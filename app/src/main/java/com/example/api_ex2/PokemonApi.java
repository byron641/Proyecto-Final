package com.example.api_ex2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonApi {

    @GET("pokemon/{name}")
    Call<Pokemon> getPokemon(@Path("name") String name);

}
