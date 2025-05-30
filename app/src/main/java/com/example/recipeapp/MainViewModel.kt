package com.example.recipeapp

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _state = mutableStateOf(BreedState())
    val state: State<BreedState> = _state

    var searchQuery by mutableStateOf("")
        private set

    var temperamentQuery by mutableStateOf("")
        private set

    init {
        fetchBreeds()
    }

    fun onSearchQueryChanged(query: String) {
        searchQuery = query
        filterBreeds()
    }

    fun onTemperamentQueryChanged(query: String) {
        temperamentQuery = query
        filterBreeds()
    }

    private fun fetchBreeds() {
        viewModelScope.launch {
            try {
                val breeds = catService.getBreeds()
                _state.value = _state.value.copy(
                    allBreeds = breeds,
                    filteredBreeds = breeds,
                    loading = false
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    loading = false,
                    error = "Failed to load breeds: ${e.message}"
                )
            }
        }
    }

    private fun filterBreeds() {
        val filtered = _state.value.allBreeds.filter {
            it.name.contains(searchQuery, ignoreCase = true) &&
                    it.temperament.contains(temperamentQuery, ignoreCase = true)
        }
        _state.value = _state.value.copy(filteredBreeds = filtered)
    }

    data class BreedState(
        val loading: Boolean = true,
        val allBreeds: List<Breed> = emptyList(),
        val filteredBreeds: List<Breed> = emptyList(),
        val error: String? = null
    )
}
