package com.example.textTrivia.service;

import com.example.textTrivia.model.Question;
import com.example.textTrivia.model.Text;
import com.example.textTrivia.model.Token;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;


import static com.example.textTrivia.model.Question.QuestionBuilder.aQuestion;

@Service
public class ChatGPTService {


    OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build();

    @Autowired
    ObjectMapper om;

    public List<QuestionIn> analyzeText(String text, int numberQuestions, String authorizationKey) throws IOException {
        text = text.replaceAll("\\s+", " ");
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n    \"model\": \"gpt-3.5-turbo\",\n    \"messages\"" +
                ": [\n        {\n            \"role\": \"system\",\n            \"content\": \"You are an " +
                "expert at analyzing a text and extracting meaningful questions that could be answered using this " +
                "text.\"\n        },\n        {\n            \"role\": \"user\",\n            \"content\": " +
                "\"I need you to create " + numberQuestions + " questions for the following text: " + text +
                " \\n For each question create 4 possible answers, when one of them is the correct one." +
                " Write the number of the correct answer.Write it only in json format as follows:" +
                " {'questions':[{'question': 'example for question', 'answers':['answer number one'," +
                " 'answer number 2',..], 'correctAnswer':number of answer}]} dont add new lines," +
                " don't add numbering to answers, don't use quotes inside answers or questions and use double quotes " +
                "for keys and values of json and number the correct answer starting from 0\"\n        }\n    ]\n}");
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + authorizationKey)
                .build();
        Response response = client.newCall(request).execute();
        return parseChatGPTResponse(response.body().string());
    }

    public List<QuestionIn> parseChatGPTResponse(String response) throws IOException {
        JsonNode jsonNode = om.readTree(response);
        String choices = jsonNode.get("choices").toString();
        choices = choices.substring(1, choices.length() - 1);
        String message = om.readTree(choices).get("message").toString();
        String content = om.readTree(message).get("content").toString();
        content = content.substring(1, content.length() - 1);

        content = content.replaceAll("(?<=\\w)'(?!\\\\s)(?=\\w)", ""); // gets rid of all single quotes that are surrounded between two characters (for example: don't)
        content = content.replaceAll("'", "\"");
        content = content.replaceAll("\\\\n", "");
        content = content.replaceAll("\\\\", "");

        return om.readValue(content, Questions.class).getQuestions();
    }

    private static class Questions {
        List<QuestionIn> questions;

        public List<QuestionIn> getQuestions() {
            return questions;
        }

        public void setQuestions(List<QuestionIn> questions) {
            this.questions = questions;
        }
    }

    public static class QuestionIn{
        String question;
        List<String> answers;
        Integer correctAnswer;

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public List<String> getAnswers() {
            return answers;
        }

        public void setAnswers(List<String> answers) {
            this.answers = answers;
        }

        public Integer getCorrectAnswer() {
            return correctAnswer;
        }

        public void setCorrectAnswer(Integer correctAnswer) {
            this.correctAnswer = correctAnswer;
        }

        public Question toQuestion(Text text, Token token){
            return aQuestion().withText(text).withQuestionContent(question).withToken(token).withFirstAnswer(answers.get(0))
                    .withSecondAnswer(answers.get(1)).withThirdAnswer(answers.get(2)).withFourthAnswer(answers.get(3)).
                    withNumberCorrectAnswer(correctAnswer).build();
        }

    }


}
