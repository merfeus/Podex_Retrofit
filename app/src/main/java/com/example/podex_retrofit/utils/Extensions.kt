package com.example.podex_retrofit.utils

fun String.toUpperFirstChar(): String{
    return replaceFirstChar { it.uppercase() }
}