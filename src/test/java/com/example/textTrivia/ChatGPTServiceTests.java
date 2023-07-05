package com.example.textTrivia;


import com.example.textTrivia.model.Question;
import com.example.textTrivia.service.ChatGPTService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Assert;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ChatGPTServiceTests {

    @Autowired
    private ChatGPTService chatGPTService;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void testParseChatGPTResponse() throws IOException {

        String response = "{\n" +
                "    \"id\": \"chatcmpl-7WHJaHu6VaH7Z7jsy5TLpAQWxtway\",\n" +
                "    \"object\": \"chat.completion\",\n" +
                "    \"created\": 1687927410,\n" +
                "    \"model\": \"gpt-3.5-turbo-0613\",\n" +
                "    \"choices\": [\n" +
                "        {\n" +
                "            \"index\": 0,\n" +
                "            \"message\": {\n" +
                "                \"role\": \"assistant\",\n" +
                "                \"content\": \"{\\n  \\\"questions\\\": [\\n    {\\n      \\\"question\\\": \\\"What is the main theme of the text?\\\",\\n      \\\"answers\\\": [\\\"A. Technology advancements\\\", \\\"B. Environmental conservation\\\", \\\"C. Social inequality\\\", \\\"D. Historical events\\\"],\\n      \\\"correctAnswer\\\": 1\\n    },\\n    {\\n      \\\"question\\\": \\\"Who is the author of the text?\\\",\\n      \\\"answers\\\": [\\\"A. John Smith\\\", \\\"B. Mary Johnson\\\", \\\"C. David Brown\\\", \\\"D. Sarah Thompson\\\"],\\n      \\\"correctAnswer\\\": 3\\n    },\\n    {\\n      \\\"question\\\": \\\"What is the purpose of the text?\\\",\\n      \\\"answers\\\": [\\\"A. To inform\\\", \\\"B. To entertain\\\", \\\"C. To persuade\\\", \\\"D. To educate\\\"],\\n      \\\"correctAnswer\\\": 0\\n    },\\n    {\\n      \\\"question\\\": \\\"Where does the story take place?\\\",\\n      \\\"answers\\\": [\\\"A. New York City\\\", \\\"B. Paris\\\", \\\"C. Tokyo\\\", \\\"D. London\\\"],\\n      \\\"correctAnswer\\\": 2\\n    },\\n    {\\n      \\\"question\\\": \\\"What is the current status of the issue discussed in the text?\\\",\\n      \\\"answers\\\": [\\\"A. Resolved\\\", \\\"B. Worsening\\\", \\\"C. Improving\\\", \\\"D. Unchanged\\\"],\\n      \\\"correctAnswer\\\": 3\\n    },\\n    {\\n      \\\"question\\\": \\\"What is the main argument presented in the text?\\\",\\n      \\\"answers\\\": [\\\"A. Economic benefits of recycling\\\", \\\"B. Importance of renewable energy\\\", \\\"C. Advantages of organic farming\\\", \\\"D. Consequences of deforestation\\\"],\\n      \\\"correctAnswer\\\": 2\\n    },\\n    {\\n      \\\"question\\\": \\\"What evidence is provided to support the author's claims?\\\",\\n      \\\"answers\\\": [\\\"A. Statistical data\\\", \\\"B. Personal anecdotes\\\", \\\"C. Expert opinions\\\", \\\"D. Historical references\\\"],\\n      \\\"correctAnswer\\\": 0\\n    },\\n    {\\n      \\\"question\\\": \\\"What is the tone of the text?\\\",\\n      \\\"answers\\\": [\\\"A. Optimistic\\\", \\\"B. Pessimistic\\\", \\\"C. Neutral\\\", \\\"D. Informative\\\"],\\n      \\\"correctAnswer\\\": 1\\n    },\\n    {\\n      \\\"question\\\": \\\"What is the main audience for this text?\\\",\\n      \\\"answers\\\": [\\\"A. Teenagers\\\", \\\"B. Business professionals\\\", \\\"C. Politicians\\\", \\\"D. Environmental activists\\\"],\\n      \\\"correctAnswer\\\": 3\\n    },\\n    {\\n      \\\"question\\\": \\\"What is the conclusion the author draws at the end of the text?\\\",\\n      \\\"answers\\\": [\\\"A. More research is needed\\\", \\\"B. Government should take action\\\", \\\"C. Individuals should change their habits\\\", \\\"D. The problem is too complex to solve\\\"],\\n      \\\"correctAnswer\\\": 2\\n    }\\n  ]\\n}\"\n" +
                "            },\n" +
                "            \"finish_reason\": \"stop\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"usage\": {\n" +
                "        \"prompt_tokens\": 136,\n" +
                "        \"completion_tokens\": 577,\n" +
                "        \"total_tokens\": 713\n" +
                "    }\n" +
                "}";

        List<ChatGPTService.QuestionIn> result = chatGPTService.parseChatGPTResponse(response);
        assertEquals(10, result.size());
        ChatGPTService.QuestionIn firstQuestion = result.get(0);
        Assert.assertNotNull(result.get(0).getCorrectAnswer());

        String responseWithSingleQuotes = response.replaceAll("\\\\\"question\\\\\"", "'question'")
                .replaceAll("\\\\\"answers\\\\\"", "'answers'").replaceAll("\\\\\"correctAnswer\\\\\"", "'correctAnswer'");

        result = chatGPTService.parseChatGPTResponse(responseWithSingleQuotes);
        assertEquals(10, result.size());
        firstQuestion = result.get(0);
        Assert.assertNotNull(result.get(0).getCorrectAnswer());

        String anotherResponse = "{\n" +
                "    \"id\": \"chatcmpl-7WKVE4AKyH9xL31SVRnD6CFPaWoHN\",\n" +
                "    \"object\": \"chat.completion\",\n" +
                "    \"created\": 1687939664,\n" +
                "    \"model\": \"gpt-3.5-turbo-0613\",\n" +
                "    \"choices\": [\n" +
                "        {\n" +
                "            \"index\": 0,\n" +
                "            \"message\": {\n" +
                "                \"role\": \"assistant\",\n" +
                "                \"content\": \"{\\\"questions\\\": [\\n  {\\n    \\\"question\\\": \\\"What is the primary contributor to global warming?\\\",\\n    \\\"answers\\\": [\\\"Deforestation\\\", \\\"Burning fossil fuels\\\", \\\"Volcanic activity\\\", \\\"Industrial waste\\\"],\\n    \\\"correctAnswer\\\": 1\\n  },\\n  {\\n    \\\"question\\\": \\\"What is the concentration of carbon dioxide (CO2) in the atmosphere?\\\",\\n    \\\"answers\\\": [\\\"Less than 100 parts per million (ppm)\\\", \\\"Around 250 parts per million (ppm)\\\", \\\"Approximately 400 parts per million (ppm)\\\", \\\"Over 500 parts per million (ppm)\\\"],\\n    \\\"correctAnswer\\\": 2\\n  },\\n  {\\n    \\\"question\\\": \\\"What adverse effects are caused by rising temperatures?\\\",\\n    \\\"answers\\\": [\\\"Decreased precipitation\\\", \\\"Reduced sea levels\\\", \\\"Increased forestation\\\", \\\"Melting of polar ice caps\\\"],\\n    \\\"correctAnswer\\\": 4\\n  },\\n  {\\n    \\\"question\\\": \\\"What actions are suggested to mitigate global warming?\\\",\\n    \\\"answers\\\": [\\\"Continued use of fossil fuels\\\", \\\"Transition to renewable energy sources\\\", \\\"Increased greenhouse gas emissions\\\", \\\"Ignoring international cooperation\\\"],\\n    \\\"correctAnswer\\\": 2\\n  }\\n]}\"\n" +
                "            },\n" +
                "            \"finish_reason\": \"stop\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"usage\": {\n" +
                "        \"prompt_tokens\": 346,\n" +
                "        \"completion_tokens\": 248,\n" +
                "        \"total_tokens\": 594\n" +
                "    }\n" +
                "}";

        result = chatGPTService.parseChatGPTResponse(anotherResponse);
        assertEquals(4, result.size());
        firstQuestion = result.get(0);
        Assert.assertNotNull(result.get(0).getCorrectAnswer());


    }

}

