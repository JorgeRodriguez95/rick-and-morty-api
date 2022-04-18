package com.example.rickandmorty.config.error;

import com.example.rickandmorty.config.exception.CharacterNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class CustomRestExceptionHandler{

    @ExceptionHandler(CharacterNotFoundException.class)
    public ResponseEntity<Map<String, String>> resourceNotFoundException() {

        Map<String, String> map = new HashMap<>();

        map.put("message", "Character not found");

        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

}
