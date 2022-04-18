package com.example.rickandmorty.helper;

import org.springframework.stereotype.Component;
import retrofit2.Response;

import java.io.IOException;

@Component
public class RickAndMortyHelper {

    public <T> T checkResponse(Response<T> response, String message) throws IOException {
        if (response.isSuccessful()) {
            return response.body();
        } else {
            try {
                if (response.errorBody() != null) {
                    throw new IOException(message + ": " + response.errorBody().string());
                }
                throw new IOException(message + ": NULL RESPONSE BODY RECEIVED");
            } catch (IOException e) {
                throw new IOException(message + ": Error getting error response body");
            }
        }
    }

    public Integer getOriginId(String url){
        String[] parts = url.split("location/");
        return Integer.parseInt(parts[1]);
    }

    public boolean isNullOrEmpty(String string){
        return (null == string || string.isEmpty());
    }

}
