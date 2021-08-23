package com.example.podex_retrofit.service

import com.example.podex_retrofit.model.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET

interface PokemonService {

    @GET("/api/v2/pokemon/")
    fun gettAll(): Call<PokemonResponse>
}