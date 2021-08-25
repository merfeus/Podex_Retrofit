package com.example.podex_retrofit.database

import androidx.room.TypeConverter
import com.example.podex_retrofit.model.Types
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun listToJson(value: List<Types>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Types>::class.java).toList()

}