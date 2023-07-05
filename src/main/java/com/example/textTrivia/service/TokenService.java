package com.example.textTrivia.service;

import com.example.textTrivia.model.Token;
import com.example.textTrivia.repo.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenService {

    @Autowired
    TokenRepository tokenRepository;

    public Optional<Token> findById(Long id){
        return tokenRepository.findById(id);
    }

    public Token save(Token token){
        return tokenRepository.save(token);
    }

}
