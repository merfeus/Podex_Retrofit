package com.example.podex_retrofit.viewmodel
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.podex_retrofit.model.Pokemon
import com.example.podex_retrofit.repository.PokemonRepository

class MainViewModel : ViewModel() {

    private val _POKE = MutableLiveData<List<Pokemon>>()
    val poke: LiveData<List<Pokemon>> = _POKE

    private val _ERROR = MutableLiveData<String>()
    val error: LiveData<String> = _ERROR


    fun fetchAllFromSerivce(context: Context) {
        val repository = PokemonRepository(context)
        repository.getPokemons(){ pokemonResponse, error ->
            pokemonResponse?.let {
                _POKE.value = it.results
                repository.insertIntoDataBase(it.results)
            }

            error?.let {
                _ERROR.value = it
            }
        }
    }

    fun fetchAllFromDataBase(context: Context){
        val listOf = PokemonRepository(context).fetchFromDataBase()
        if (listOf != null && listOf.isNotEmpty()) {
            _POKE.value = listOf!!
        } else {
            fetchAllFromSerivce(context)
        }
    }
}
