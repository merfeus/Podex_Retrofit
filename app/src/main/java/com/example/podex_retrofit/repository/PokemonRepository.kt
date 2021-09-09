package com.example.podex_retrofit.repository

import com.example.podex_retrofit.database.dao.PokemonDAO
import com.example.podex_retrofit.model.Pokemon
import com.example.podex_retrofit.model.PokemonDetails
import com.example.podex_retrofit.service.PokemonService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class PokemonRepository(private val pokemonDAO: PokemonDAO, private val service: PokemonService) {

    suspend fun getPokemons(): List<Pokemon>? {
        return withContext(CoroutineScope(Dispatchers.Default).coroutineContext) {
            val response = service.getAll()
            val responsePokemon = processData(response)
            responsePokemon?.results?.forEach {
                fetchPokemonDetails(it.extractIdFormUrl())?.let { details ->
                    it.details = details
                }
            }
            responsePokemon?.results
        }

    }

    private fun <T> processData(response: Response<T>): T? {
        return if (response.isSuccessful) response.body() else null
    }


    suspend fun fetchPokemonDetails(pokeId: String): PokemonDetails? {

        return withContext(CoroutineScope(Dispatchers.Default).coroutineContext) {
            val response = service.getDetials(pokeId)
            processData(response)
        }
    }

    fun insertIntoDatabase(pokemon: Pokemon) {
        return pokemonDAO.insert(pokemon = pokemon)
    }

    fun fetchFromDataBase(): List<Pokemon>? {
        return pokemonDAO.getAll()
    }

    fun fetchAllFromDataBaseWithFilter(query: String): List<Pokemon>? {
        return pokemonDAO.fetchFiltered(query)
    }
}
