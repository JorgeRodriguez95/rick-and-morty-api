package com.example.rickandmorty.controller;

import com.example.rickandmorty.dto.CharacterDto;
import com.example.rickandmorty.service.CharacterService;
import org.springframework.stereotype.Component;

@Component
public class CharacterController {

    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    public CharacterDto getCharacter(Integer id){
        return characterService.getCharacter(id);
    }

}
