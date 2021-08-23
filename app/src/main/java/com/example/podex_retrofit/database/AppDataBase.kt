package com.example.podex_retrofit.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.podex_retrofit.database.dao.PokemonDAO
import com.example.podex_retrofit.model.Pokemon

@Database(entities = [Pokemon::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun pokemonDAO(): PokemonDAO

    companion object {

        fun getDataBase(context: Context): AppDataBase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java,
                "pokemon_app_db"
            )
                .allowMainThreadQueries()
                .build()
        }
    }

}