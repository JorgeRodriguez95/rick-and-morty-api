package com.example.rickandmorty.data;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Character {
    private Integer id;
    private String name;
    private String status;
    private String species;
    private String type;
    private List<String> episode;
    private Location origin;
}
