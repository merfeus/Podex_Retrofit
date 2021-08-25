package com.example.podex_retrofit.repository

import android.content.Context
import com.example.podex_retrofit.database.AppDataBase
import com.example.podex_retrofit.model.Pokemon
import com.example.podex_retrofit.model.PokemonDetails
import com.example.podex_retrofit.model.PokemonResponse
import com.example.podex_retrofit.service.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonRepository(private val context: Context) {

    private val dataBase = AppDataBase.getDataBase(context)
    val service = RetrofitBuilder.getPokemonService()

    fun getPokemons(onComplete: (PokemonResponse?, String?) -> Unit) {
        val call = service.getAll()
        call.enqueue(object : Callback<PokemonResponse> {
            override fun onResponse(call: Call<PokemonResponse>, response: Response<PokemonResponse>) {
                if (response.body() != null) {
                    onComplete(response.body(), null)
                } else {
                    onComplete(null, "Nenhum pokemon encontrado")
                }
            }

            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                onComplete(null, t.message)
            }
        })
    }

    fun fetchPokemonDetails(pokeId: String, onComplete:(PokemonDetails?,String?) -> Unit){
        val call = service.getDetials(pokeId)
        call.enqueue(object : Callback<PokemonDetails>{

            override fun onResponse(
                call: Call<PokemonDetails>,
                response: Response<PokemonDetails>
            ) {
                if (response.body() != null){
                    onComplete(response.body(), null)
                } else {
                    onComplete(null, "Nenhum pokemon encontrado")
                }
            }

            override fun onFailure(call: Call<PokemonDetails>, t: Throwable) {
                onComplete(null, t.message)
            }

        })
    }

    fun insertIntoDataBase(items: List<Pokemon>){
        val dao = dataBase.pokemonDAO()
        items.forEach {
            dao.insert(pokemon = it)
        }//nao esta sendo utilizada
    }

    fun insertIntoDatabase(pokemon: Pokemon) {
        val dao = dataBase.pokemonDAO()
        dao.insert(pokemon = pokemon)
    }

    fun fetchFromDataBase(): List<Pokemon>?{
        val dao = dataBase.pokemonDAO()
        return dao.getAll()
    }

    fun fetchAllFromDataBaseWithFilter(query: String): List<Pokemon>?{
        val dao = dataBase.pokemonDAO()
        return dao.fetchFiltered(query)
    }
}