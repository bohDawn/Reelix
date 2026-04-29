package com.bohdawn.reelix.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bohdawn.reelix.models.Movie
import com.bohdawn.reelix.models.MovieResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val httpClient: HttpClient) : ViewModel() {

    private val _moviesList = MutableStateFlow<List<Movie>>(emptyList())
    val moviesList: StateFlow<List<Movie>> = _moviesList.asStateFlow()

    private val _selectedMovie = MutableStateFlow<Movie?>(null)
    val selectedMovie: StateFlow<Movie?> = _selectedMovie.asStateFlow()

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            try {
                val response: MovieResponse = httpClient.get("movie/popular").body()
                _moviesList.value = response.results
            } catch (e: Exception) {
                println("Помилка завантаження фільмів: ${e.message}")
            }
        }
    }

    fun selectMovie(movie: Movie) {
        _selectedMovie.value = movie
    }

    fun closeDetails() {
        _selectedMovie.value = null
    }
}