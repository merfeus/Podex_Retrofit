package com.example.podex_retrofit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.podex_retrofit.model.PokeTypeSetup

class FilterViewModel : ViewModel() {

    private val _TYPE = MutableLiveData<List<PokeTypeSetup>>()
    val poke: LiveData<List<PokeTypeSetup>> = _TYPE


}

