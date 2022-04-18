package com.example.rickandmorty.service.impl;

import com.example.rickandmorty.config.exception.CharacterNotFoundException;
import com.example.rickandmorty.data.Location;
import com.example.rickandmorty.data.Character;
import com.example.rickandmorty.dto.CharacterDto;
import com.example.rickandmorty.helper.RickAndMortyHelper;
import com.example.rickandmorty.helper.mapper.CharacterMapperHelper;
import com.example.rickandmorty.proxy.api.RickAndMortyApi;
import com.example.rickandmorty.service.CharacterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@Service
public class CharacterServiceImpl implements CharacterService {

    private final RickAndMortyApi rickAndMortyApi;
    private final RickAndMortyHelper rickAndMortyHelper;
    private final CharacterMapperHelper characterMapperHelper;

    public CharacterServiceImpl(RickAndMortyApi rickAndMortyApi, RickAndMortyHelper rickAndMortyHelper, CharacterMapperHelper characterMapperHelper) {
        this.rickAndMortyApi = rickAndMortyApi;
        this.rickAndMortyHelper = rickAndMortyHelper;
        this.characterMapperHelper = characterMapperHelper;
    }

    @Override
    public CharacterDto getCharacter(Integer id) {
        log.error("Search by id: {}", id);
        Character root = getCharacterApi(id);
        if(Objects.isNull(root)){
            log.error("Character not found");
            throw new CharacterNotFoundException();
        }
        boolean isNull = rickAndMortyHelper.isNullOrEmpty(root.getOrigin().getUrl());
        if(!isNull){
            Location origin = getOriginApi(root.getOrigin().getUrl());
            root.setOrigin(origin);
        }
        return characterMapperHelper.dataToDto(root);
    }

    private Character getCharacterApi(Integer id){
        try {
            Response<Character> response = rickAndMortyApi.getCharacter(id).execute();
            return rickAndMortyHelper.checkResponse(response, "Error getting character in Rick and Morty api");
        } catch (IOException e) {
            log.error("Excepcion Api Character");
        }
        return null;
    }

    private Location getOriginApi(String url){
        Integer id = rickAndMortyHelper.getOriginId(url);
        try {
            Response<Location> response = rickAndMortyApi.getLocation(id).execute();
            return rickAndMortyHelper.checkResponse(response, "Error getting Location in Rick and Morty api");
        } catch (IOException e) {
            log.error("Excepcion Api Location");
        }
        return null;
    }
}
