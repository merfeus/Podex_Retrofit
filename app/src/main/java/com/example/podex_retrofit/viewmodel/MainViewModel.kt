package com.example.podex_retrofit.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.podex_retrofit.model.Pokemon
import com.example.podex_retrofit.repository.PokemonRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _POKE = MutableLiveData<List<Pokemon>>()
    val poke: LiveData<List<Pokemon>> = _POKE

    private val _ERROR = MutableLiveData<String>()
    val error: LiveData<String> = _ERROR

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    fun fetchAllFromServer(context: Context) {

        val repository = PokemonRepository(context)

        _isLoading.value = true

        viewModelScope.launch {
            repository.getPokemons()?.let { pokemons ->
                _isLoading.value = false
                _POKE.value = pokemons
            }
        }
    }

    fun fetchAllFromDatabase(context: Context) {

        val listOf = PokemonRepository(context).fetchFromDataBase()

        if (listOf != null && listOf.isNotEmpty()) {
            _POKE.value = listOf!!
        } else {
            fetchAllFromServer(context)
        }

    }
}
