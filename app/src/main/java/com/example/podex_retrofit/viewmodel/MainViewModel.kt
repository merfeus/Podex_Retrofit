package com.example.podex_retrofit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.podex_retrofit.model.Pokemon
import com.example.podex_retrofit.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: PokemonRepository) : ViewModel() {

    private val _POKE = MutableLiveData<List<Pokemon>>()
    val poke: LiveData<List<Pokemon>> = _POKE

    private val _ERROR = MutableLiveData<String>()
    val error: LiveData<String> = _ERROR

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    fun fetchAllFromServer() {
        _isLoading.value = true
        viewModelScope.launch {
            repository.getPokemons()?.let {
                _isLoading.value = false
                _POKE.value = it
            }
        }
    }

    fun fetchAllFromDatabase() {
        val listOfPoke = repository.fetchFromDataBase()
        if (listOfPoke != null && listOfPoke.isNotEmpty()) {
            _POKE.value = listOfPoke!!
        } else {
            fetchAllFromServer()
        }

    }

    fun fetchFilteredFromDatabase(query: String) {
        val filteredList = repository.fetchAllFromDataBaseWithFilter(query)
        filteredList?.let {
            _POKE.value = it
        }
    }

}
