package com.example.textTrivia.service;

import com.example.textTrivia.model.AnalysisParameters;
import com.example.textTrivia.model.Question;
import com.example.textTrivia.model.Text;
import com.example.textTrivia.model.Token;
import com.example.textTrivia.repo.TextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TextService {

    @Autowired
    TextRepository textRepository;

    @Autowired
    ChatGPTService chatGPTService;

    @Autowired
    QuestionService questionService;

    @Autowired
    TokenService tokenService;

    public Text save(Text text){
        return textRepository.save(text);
    }
    public Optional<Text> findById(Long id){
        return textRepository.findById(id);
    }

    public Iterable<Text> all(){
        return textRepository.findAll();
    }

    @Async
    public void analyzeText(Long textId, AnalysisParameters analysisParameters, Token token){
        Optional<Text> dbText = findById(textId);
        Text text = dbText.get();
        try{
            List<ChatGPTService.QuestionIn> questionIns = chatGPTService.analyzeText(text.getValue(),
                    analysisParameters.getNumberOfQuestions(), analysisParameters.getAuthorizationKey());
            for(ChatGPTService.QuestionIn questionIn: questionIns){
                Question question = questionIn.toQuestion(text, token);
                questionService.save(question);
            }
            token.setReady(true);
            tokenService.save(token);
        }catch (Exception e){
            System.out.println(e.getMessage());
            token.setHasAnError(true);
            tokenService.save(token);
        }
    }

}
