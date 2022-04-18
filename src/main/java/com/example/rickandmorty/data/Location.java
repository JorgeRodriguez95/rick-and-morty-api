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
public class Location {
    private String name;
    private String url;
    private String dimension;
    private List<String> residents;
}
