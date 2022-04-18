package com.example.rickandmorty.boundary;

import com.example.rickandmorty.controller.CharacterController;
import com.example.rickandmorty.dto.CharacterDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rick-and-morty")
@CrossOrigin("*")
public class CharacterBoundary {

    private final CharacterController characterController;

    public CharacterBoundary(CharacterController characterController) {
        this.characterController = characterController;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterDto> getCharacter(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(characterController.getCharacter(id), HttpStatus.OK);
    }
}
