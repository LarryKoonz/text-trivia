package com.example.textTrivia.service;

import com.example.textTrivia.model.Answer;
import com.example.textTrivia.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    @Autowired
    QuestionService questionService;

    public List<Boolean> getScore(List<Answer> answers){
        List<Boolean> result = new ArrayList<>();
        for(Answer answer: answers){
            long questionId = answer.getQuestionId();
            Optional<Question> dbQuestion = questionService.findById(questionId);
            if (dbQuestion.isEmpty()) throw new RuntimeException("Question with id: " + questionId + " not found");
            int rightAnswer = dbQuestion.get().getNumberCorrectAnswer();
            if (answer.getSelectedAnswer() == rightAnswer)
                result.add(true);
            else
                result.add(false);
        }
        return result;
    }

}
