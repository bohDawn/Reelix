package com.bohdawn.reelix.viewmodels

import androidx.lifecycle.ViewModel
import Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {

    private val _moviesList = MutableStateFlow(
        // TODO: Replace this hardcoded list with real data from TMDB API
        List(20) { index ->
            Movie(
                id = index,
                title = "Venom: The Last Dance",
                posterUrl = "https://image.tmdb.org/t/p/w500/aosm8NMQ3UyoBVpSxyimorCQykC.jpg",
                rating = 8.5
            )
        }
    )
    val moviesList: StateFlow<List<Movie>> = _moviesList.asStateFlow()
    private val _selectedMovie = MutableStateFlow<Movie?>(null)
    val selectedMovie: StateFlow<Movie?> = _selectedMovie.asStateFlow()

    fun selectMovie(movie: Movie) {
        _selectedMovie.value = movie
    }

    fun clearSelection() {
        _selectedMovie.value = null
    }
}