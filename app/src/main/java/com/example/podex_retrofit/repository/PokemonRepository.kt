package com.example.podex_retrofit.repository

import com.example.podex_retrofit.model.PokemonResponse
import com.example.podex_retrofit.service.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonRepository {

    fun getPokemons(callback: (PokemonResponse?, String?) -> Unit) {
        val retrofitService = RetrofitBuilder.getPokemonService()
        val call = retrofitService.gettAll()

        call.enqueue(object : Callback<PokemonResponse> {
            override fun onResponse(
                call: Call<PokemonResponse>,
                response: Response<PokemonResponse>
            ) {
                if (response.body() != null) {
                    response.body()?.let { pokeResponse ->
                        callback(pokeResponse, null)
                    }
                } else {
                    callback(null, "Algum outro tipo de erro")
                }
            }

            override fun onFailure(call: Call<PokemonResponse>, exception: Throwable) {
                callback(null, exception.message)
            }
        })
    }

}