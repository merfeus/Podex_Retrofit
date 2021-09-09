package com.example.podex_retrofit.di

import android.content.Context
import com.example.podex_retrofit.database.AppDataBase
import com.example.podex_retrofit.database.dao.PokemonDAO
import com.example.podex_retrofit.repository.PokemonRepository
import com.example.podex_retrofit.service.PokemonService
import com.example.podex_retrofit.service.RetrofitBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    //Provide DAO Context
    @Provides
    fun providePokeDataBase(@ApplicationContext context: Context): PokemonDAO {
        return AppDataBase.getDataBase(context).pokemonDAO()
    }

    //Provides Repository

    @Provides
    fun providesPokemonRepository(@ApplicationContext context: Context, service: PokemonService) : PokemonRepository{
        return PokemonRepository(providePokeDataBase(context), service)
    }

    @Provides
    fun providePokemonService(): PokemonService = RetrofitBuilder.getPokemonService()
}