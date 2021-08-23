package com.example.podex_retrofit.model

data class PokemonResponse(val results: List<Pokemon>)

data class Pokemon(
    val name: String?,
    val url: String?
)