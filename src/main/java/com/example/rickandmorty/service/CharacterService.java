package com.example.rickandmorty.service;

import com.example.rickandmorty.dto.CharacterDto;


public interface CharacterService {
    CharacterDto getCharacter(Integer id);
}
