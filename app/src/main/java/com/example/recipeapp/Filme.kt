package com.example.recipeapp


data class Filme(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String?,
    val backdrop_path: String?,
    val vote_average: Double,
    val release_date: String
)

fun RatedMovie.toFilme() = Filme(
    id = id,
    title = title,
    overview = overview,
    poster_path = poster_path,
    backdrop_path = TODO(),
    vote_average = TODO(),
    release_date = TODO()
)