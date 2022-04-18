package com.example.rickandmorty.dto;

import com.example.rickandmorty.data.Location;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class CharacterDto {
    private Integer id;
    private String name;
    private String status;
    private String species;
    private String type;
    private Integer episodeCount;
    private Location origin;
}
