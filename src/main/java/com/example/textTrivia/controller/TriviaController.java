package com.example.textTrivia.controller;

import com.example.textTrivia.model.Text;
import com.example.textTrivia.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TriviaController {

    @Autowired
    TextService textService;

    @GetMapping("/homepage")
    public String homePage(Model model){
        return "homepage";
    }

    @GetMapping("/answer-questions")
    public String answerQuestions(Model model){
        Iterable<Text> texts = textService.all();
        model.addAttribute("texts", texts);
        return "answer-questions";
    }

    @GetMapping("/create-questions")
    public String createQuestions(Model model){
        Iterable<Text> texts = textService.all();
        model.addAttribute("texts", texts);
        return "create-questions";
    }

    @GetMapping("/enter-text")
    public String enterText(Model model){
        return "enter-text";
    }

}
