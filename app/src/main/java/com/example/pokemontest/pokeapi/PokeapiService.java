package com.example.pokemontest.pokeapi;

import retrofit2.Call;

import com.example.pokemontest.models.PokeapiRequest;
import com.example.pokemontest.models.Pokedet;
import com.example.pokemontest.models.Pokemon;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokeapiService {

    @GET("pokemon")
    Call<PokeapiRequest> ListPokemon(@Query("limit") int limit, @Query("offset") int offset);

    @GET("pokemon/{id}")
    Call<List<Pokedet>> getPokemonById(@Path("id") int id);

    @GET("pokemon/{name}")
    Call<List<Pokedet>> getPokemonByName(@Path("name") String name);

}
