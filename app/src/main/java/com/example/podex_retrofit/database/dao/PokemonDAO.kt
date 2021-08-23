package com.example.podex_retrofit.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.podex_retrofit.model.Pokemon

@Dao
interface PokemonDAO {

    @Query("SELECT * FROM Pokemon ORDER BY poke_nome")
    fun getAll(): List<Pokemon>

    @Query("SELECT * FROM Pokemon WHERE poke_nome = :pokeId")
    fun byId(pokeId: String): Pokemon

    @Insert
    fun insert(pokemon: Pokemon)
}