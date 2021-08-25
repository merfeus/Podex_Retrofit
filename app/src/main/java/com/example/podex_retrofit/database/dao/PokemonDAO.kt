package com.example.podex_retrofit.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.podex_retrofit.model.Pokemon
import com.example.podex_retrofit.model.Types

@Dao
interface PokemonDAO {

    @Query("SELECT * FROM Pokemon")
    fun getAll(): List<Pokemon>?

    @Query("SELECT * FROM Pokemon WHERE poke_nome = :pokeId")
    fun byId(pokeId: String): Pokemon?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pokemon: Pokemon)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertType(types: List<Types>)

    @Query("SELECT * FROM pokemon WHERE poke_nome LIKE '%' || :query || '%'")
    fun fetchFiltered(query: String) : List<Pokemon>


}