package com.example.rickandmorty.helper.mapper;

import com.example.rickandmorty.data.Character;
import com.example.rickandmorty.dto.CharacterDto;
import org.springframework.stereotype.Component;

@Component
public class CharacterMapperHelper implements Mapper<CharacterDto, Character> {

    @Override
    public CharacterDto dataToDto(Character data) {
        return CharacterDto.builder()
                .id(data.getId())
                .name(data.getName())
                .status(data.getStatus())
                .species(data.getSpecies())
                .type(data.getType())
                .episodeCount(data.getEpisode().size())
                .origin(data.getOrigin())
                .build();
    }
}
