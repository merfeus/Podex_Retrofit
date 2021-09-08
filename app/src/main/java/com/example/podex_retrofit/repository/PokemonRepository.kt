package com.example.podex_retrofit.repository

import android.content.Context
import com.example.podex_retrofit.database.AppDataBase
import com.example.podex_retrofit.model.Pokemon
import com.example.podex_retrofit.model.PokemonDetails
import com.example.podex_retrofit.service.RetrofitBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class PokemonRepository(private val context: Context) {

    private val dataBase = AppDataBase.getDataBase(context)
    val service = RetrofitBuilder.getPokemonService()

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


    fun insertIntoDataBase(items: List<Pokemon>) {
        val dao = dataBase.pokemonDAO()
        items.forEach {
            dao.insert(pokemon = it)
        }
    }

    fun insertIntoDatabase(pokemon: Pokemon) {
        val dao = dataBase.pokemonDAO()
        dao.insert(pokemon = pokemon)
    }

    fun fetchFromDataBase(): List<Pokemon>? {
        val dao = dataBase.pokemonDAO()
        return dao.getAll()
    }

    fun fetchAllFromDataBaseWithFilter(query: String): List<Pokemon>? {
        val dao = dataBase.pokemonDAO()
        return dao.fetchFiltered(query)
    }
}
