package com.example.podex_retrofit.service

import com.example.podex_retrofit.model.PokemonDetails
import com.example.podex_retrofit.model.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {


    @GET("/api/v2/pokemon?limit=1118")
    fun getAll(): Call<PokemonResponse>

    @GET("/api/v2/pokemon/{id}")
    fun getDetials(@Path("id") id: String): Call<PokemonDetails>
}