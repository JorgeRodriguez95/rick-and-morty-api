package com.example.rickandmorty.dto;

import lombok.Data;

import java.util.List;

@Data
public class LocationDto {
    private String name;
    private String url;
    private String dimension;
    private List<String> residents;
}
