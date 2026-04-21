package com.bohdawn.reelix.viewmodels

import androidx.lifecycle.ViewModel
import com.bohdawn.reelix.models.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {

    private val _moviesList = MutableStateFlow<List<Movie>>(emptyList())
    val moviesList: StateFlow<List<Movie>> = _moviesList.asStateFlow()

    private val _selectedMovie = MutableStateFlow<Movie?>(null)
    val selectedMovie: StateFlow<Movie?> = _selectedMovie.asStateFlow()

    fun selectMovie(movie: Movie) {
        _selectedMovie.value = movie
    }

    fun closeDetails() {
        _selectedMovie.value = null
    }
}