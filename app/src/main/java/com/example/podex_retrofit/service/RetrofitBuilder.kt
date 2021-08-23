package com.example.podex_retrofit.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private val retrofitPokemon = Retrofit.Builder()
        .baseUrl("https://pokeapi.co")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getPokemonService(): PokemonService{
        return retrofitPokemon.create(PokemonService::class.java)
    }

}