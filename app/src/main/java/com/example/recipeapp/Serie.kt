package com.example.recipeapp


data class Serie(
    val id: Int,
    val name: String,
    val overview: String,
    val poster_path: String?,
    val backdrop_path: String?,
    val vote_average: Double,
    val first_air_date: String
)
