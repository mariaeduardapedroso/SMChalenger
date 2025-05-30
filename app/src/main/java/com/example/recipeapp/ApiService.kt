package com.example.recipeapp

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

private val retrofit = Retrofit.Builder()
    .baseUrl("https://api.themoviedb.org/3/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val tmdbApi = retrofit.create(TmdbApi::class.java)

interface TmdbApi {
    @GET("movie/popular")
    suspend fun getRatedMovies(
        @Header("Authorization") auth: String = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjNmQ4OTYwMjNlYjM2YzU4ODNmOGVlNDI4ZmQ2MDI5YyIsIm5iZiI6MTY3MDk0MzYwMC42MTYsInN1YiI6IjYzOTg5MzcwMmNlZmMyMDA4NGI2NWRkNCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.-FsuE20UhArUVEdq0mRYkuWriUZeHHreGixiZi8AxgM",
        @Header("accept") accept: String = "application/json"
    ): Response<PagedResponse<RatedMovie>>

    @GET("account/{account_id}/rated/tv")
    suspend fun getRatedTv(
        @Path("account_id") accountId: String,
        @Header("Authorization") auth: String = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjNmQ4OTYwMjNlYjM2YzU4ODNmOGVlNDI4ZmQ2MDI5YyIsIm5iZiI6MTY3MDk0MzYwMC42MTYsInN1YiI6IjYzOTg5MzcwMmNlZmMyMDA4NGI2NWRkNCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.-FsuE20UhArUVEdq0mRYkuWriUZeHHreGixiZi8AxgM",
        @Header("accept") accept: String = "application/json"
    ): Response<PagedResponse<RatedTv>>

}

data class PagedResponse<T>(
    val page: Int = 0,
    val results: List<T> = emptyList(),
    val total_pages: Int = 0,
    val total_results: Int = 0
)

data class RatedMovie(
    val adult: Boolean = true,
    val backdrop_path: String?,
    val genre_ids: List<Int>,
    val id: Int = 0,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double = 0.0,
    val poster_path: String?,
    val release_date: String,
    val title: String,
    val video: Boolean = true,
    val vote_average: Double = 0.0,
    val vote_count: Int = 0,
    val rating: Int = 0
)


data class RatedTv(
    val adult: Boolean = true,
    val backdrop_path: String?,
    val genre_ids: List<Int>,
    val id: Int = 0,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double = 0.0,
    val poster_path: String?,
    val first_air_date: String,
    val name: String,
    val vote_average: Double = 0.0,
    val vote_count: Int = 0,
    val rating: Int = 0
)
