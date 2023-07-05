package com.example.textTrivia.controller;


import com.example.textTrivia.model.Token;
import com.example.textTrivia.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    TokenService tokenService;

    @RequestMapping(value = "/{tokenId}", method = RequestMethod.GET)
    public ResponseEntity<?> getToken(@PathVariable Long tokenId){
        Optional<Token> dbToken = tokenService.findById(tokenId);
        if (dbToken.isEmpty()) throw new RuntimeException("Token with id: " + tokenId + " not found");
        Token token = dbToken.get();
        return new ResponseEntity<>(token, HttpStatus.OK);
    }




}
