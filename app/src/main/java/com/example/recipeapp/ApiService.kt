package com.example.recipeapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val retrofit = Retrofit.Builder()
    .baseUrl("https://api.thecatapi.com/v1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val catService: ApiService = retrofit.create(ApiService::class.java)

interface ApiService {
    @GET("breeds")
    suspend fun getBreeds(): List<Breed>
}
