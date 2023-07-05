package com.example.textTrivia.controller;

import com.example.textTrivia.model.Answer;
import com.example.textTrivia.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> getScore(@RequestBody List<Answer> answers){
        List<Boolean> score = answerService.getScore(answers);
        return new ResponseEntity<>(score, HttpStatus.OK);
    }

}
