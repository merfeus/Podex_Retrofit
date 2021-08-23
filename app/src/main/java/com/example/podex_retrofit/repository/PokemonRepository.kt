package com.example.podex_retrofit.repository

import android.content.Context
import com.example.podex_retrofit.database.AppDataBase
import com.example.podex_retrofit.model.Pokemon
import com.example.podex_retrofit.model.PokemonResponse
import com.example.podex_retrofit.service.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonRepository(private val context: Context) {

    private val dataBase = AppDataBase.getDataBase(context)

    fun getPokemons(callback: (PokemonResponse?, String?) -> Unit) {
        val retrofitService = RetrofitBuilder.getPokemonService()
        val call = retrofitService.gettAll()

        call.enqueue(object : Callback<PokemonResponse> {
            override fun onResponse(
                call: Call<PokemonResponse>,
                response: Response<PokemonResponse>
            ) {
                if (response.body() != null) {
                    callback(response.body(), null)
                } else {
                    callback(null, "Algum outro tipo de erro")
                }
            }

            override fun onFailure(call: Call<PokemonResponse>, exception: Throwable) {
                callback(null, exception.message)
            }
        })
    }

    fun insertIntoDataBase(items: List<Pokemon>){
        val dao = dataBase.pokemonDAO()
        items.forEach {
            dao.insert(pokemon = it)
        }
    }

    fun fetchFromDataBase(): List<Pokemon>{
        val dao = dataBase.pokemonDAO()
        return dao.getAll()
    }
}