package com.example.rickandmorty.helper.mapper;

public interface Mapper <D, Data>{

    D dataToDto(Data data);
}
