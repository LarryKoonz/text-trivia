package com.example.textTrivia.controller;

import com.example.textTrivia.model.*;
import com.example.textTrivia.service.ChatGPTService;
import com.example.textTrivia.service.QuestionService;
import com.example.textTrivia.service.TextService;
import com.example.textTrivia.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;


@RestController
@RequestMapping("/text")
public class TextController {

    @Autowired
    TextService textService;

    @Autowired
    TokenService tokenService;

    @Autowired
    ChatGPTService chatGPTService;

    @Autowired
    QuestionService questionService;


    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> insertText(@RequestBody TextIn textIn) {
        Text text = textIn.toText();
        text = textService.save(text);
        return new ResponseEntity<>(text, HttpStatus.OK);
    }

    @RequestMapping(value = "/{textId}/questions", method = RequestMethod.POST)
    public ResponseEntity<?> createQuestionsFromText(@PathVariable Long textId, @RequestBody AnalysisParameters analysisParameters){
        Optional<Text> dbText = textService.findById(textId);
        if (dbText.isEmpty()) throw new RuntimeException("Text with id: " + textId + " not found");
        Token token = new Token();
        token = tokenService.save(token);
        textService.analyzeText(textId, analysisParameters, token);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @RequestMapping(value = "/{textId}/questions", method = RequestMethod.GET)
    public ResponseEntity<?> createQuestionsFromText(@PathVariable Long textId){
        Iterable<Question> questions = questionService.findByTextId(textId);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }



}
