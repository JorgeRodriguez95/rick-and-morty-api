package com.example.rickandmorty.service.impl;

import com.example.rickandmorty.config.exception.CharacterNotFoundException;
import com.example.rickandmorty.data.Character;
import com.example.rickandmorty.data.Location;
import com.example.rickandmorty.dto.CharacterDto;
import com.example.rickandmorty.helper.RickAndMortyHelper;
import com.example.rickandmorty.helper.mapper.CharacterMapperHelper;
import com.example.rickandmorty.proxy.api.RickAndMortyApi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@WebMvcTest(CharacterServiceImplTest.class)
@AutoConfigureMockMvc
class CharacterServiceImplTest {

    @InjectMocks
    CharacterServiceImpl characterService;

    @Mock
    RickAndMortyApi rickAndMortyApi;

    @Mock
    RickAndMortyHelper rickAndMortyHelper;

    @Mock
    Call<Character> characterCall;

    @Mock
    Call<Location> locationCall;

    @Mock
    CharacterMapperHelper characterMapperHelper;

    Character characterResponse;
    
    @BeforeEach
    void setUp() {
        characterResponse =  Character.builder()
                .id(1)
                .status("Alive")
                .species("")
                .episode(Arrays.asList(
                        "https://rickandmortyapi.com/api/episode/1",
                        "https://rickandmortyapi.com/api/episode/2",
                        "https://rickandmortyapi.com/api/episode/3"
                ))
                .origin(Location.builder()
                        .url("https://rickandmortyapi.com/api/location/3").build())
                .build();
    }

    @Test
    void getCharacter_caseSuccess() throws IOException {

        CharacterDto characterDto = CharacterDto.builder().build();;

        Location locationResponse = Location.builder().build();

        when(rickAndMortyApi.getCharacter(any())).thenReturn(characterCall);

        when(characterCall.execute()).thenReturn(Response.success(characterResponse));

        when(rickAndMortyHelper.checkResponse(ArgumentMatchers.any(), anyString())).
                thenReturn(characterResponse)
                .thenReturn(locationResponse);

        when(rickAndMortyApi.getLocation(any())).thenReturn(locationCall);

        when(locationCall.execute()).thenReturn(Response.success(locationResponse));

        when(characterMapperHelper.dataToDto(characterResponse)).thenReturn(CharacterDto.builder().build());

        CharacterDto response = characterService.getCharacter(any());

        assertEquals(characterDto.getName(), response.getName());

    }

    @Test
    void getCharacter_caseFailedCharacter() throws IOException {

        when(rickAndMortyApi.getCharacter(any())).thenReturn(characterCall);

        when(characterCall.execute()).thenThrow(new IOException("test"));

        assertThrows(CharacterNotFoundException.class, () -> characterService.getCharacter(1));

    }

    @Test
    void getCharacter_caseFailedLocation() throws IOException {

        CharacterDto characterDto = CharacterDto.builder().build();;

        Location locationResponse = Location.builder().build();

        when(rickAndMortyApi.getCharacter(any())).thenReturn(characterCall);

        when(characterCall.execute()).thenReturn(Response.success(characterResponse));

        when(rickAndMortyHelper.checkResponse(ArgumentMatchers.any(), anyString())).
                thenReturn(characterResponse)
                .thenReturn(locationResponse);

        when(rickAndMortyApi.getLocation(any())).thenReturn(locationCall);

        when(locationCall.execute()).thenThrow(new IOException("test"));

        when(characterMapperHelper.dataToDto(characterResponse)).thenReturn(CharacterDto.builder().build());

        CharacterDto response = characterService.getCharacter(any(Integer.TYPE));

        assertEquals(characterDto.getName(), response.getName());

    }
}