package com.example.recipeapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.TmdbResponse
import com.example.recipeapp.TmdbApi
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class FilmeViewModel : ViewModel() {

    private val _filmesState = mutableStateOf<List<Filme>>(emptyList())
    val filmesState: State<List<Filme>> = _filmesState

    private val _tvSeriesState = mutableStateOf<List<Serie>>(emptyList())
    val tvSeriesState: State<List<Serie>> = _tvSeriesState

    init {
        fetchTopRatedMovies()
        fetchTopRatedSeries()
    }

    private fun fetchTopRatedMovies() {
        viewModelScope.launch {
            try {
                val response: TmdbResponse<Filme> = TmdbApi.service.getTopRatedMovies()
                _filmesState.value = response.results
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun fetchTopRatedSeries() {
        viewModelScope.launch {
            try {
                val response: TmdbResponse<Serie> = TmdbApi.service.getTopRatedTvSeries()
                _tvSeriesState.value = response.results
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
