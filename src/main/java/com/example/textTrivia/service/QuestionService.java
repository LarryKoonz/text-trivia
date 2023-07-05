package com.example.textTrivia.service;

import com.example.textTrivia.model.Question;
import com.example.textTrivia.model.Text;
import com.example.textTrivia.repo.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public Question save(Question question){
        return questionRepository.save(question);
    }
    public Optional<Question> findById(Long id){
        return questionRepository.findById(id);
    }

    public Iterable<Question> findByTextId(long id){
        return questionRepository.findQuestionsByTextId(id);
    }


}
