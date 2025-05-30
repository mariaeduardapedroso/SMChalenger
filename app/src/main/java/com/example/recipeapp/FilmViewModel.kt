package com.example.recipeapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class FilmeViewModel : ViewModel() {

    private val accountId = "16441102"

    private val _filmesState = mutableStateOf<List<RatedMovie>>(emptyList())
    val filmesState: State<List<RatedMovie>> = _filmesState

    private val _tvSeriesState = mutableStateOf<List<RatedTv>>(emptyList())
    val tvSeriesState: State<List<RatedTv>> = _tvSeriesState

    init {
        fetchRatedMovies()
        fetchRatedTvSeries()
    }

    private fun fetchRatedMovies() {
        viewModelScope.launch {
            try {
                val response = tmdbApi.getRatedMovies(accountId)
                if (response.isSuccessful) {
                    _filmesState.value = response.body()?.results ?: emptyList()
                } else {
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun fetchRatedTvSeries() {
        viewModelScope.launch {
            try {
                val response = tmdbApi.getRatedTv(accountId)
                if (response.isSuccessful) {
                    _tvSeriesState.value = response.body()?.results ?: emptyList()
                } else {
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
