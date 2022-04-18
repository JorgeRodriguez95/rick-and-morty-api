package com.example.rickandmorty.proxy.api;

import com.example.rickandmorty.data.Location;
import com.example.rickandmorty.data.Character;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RickAndMortyApi {

    @GET("character/{id}")
    Call<Character> getCharacter(@Path("id") Integer id);

    @GET("location/{id}")
    Call<Location> getLocation(@Path("id") Integer id);
}
