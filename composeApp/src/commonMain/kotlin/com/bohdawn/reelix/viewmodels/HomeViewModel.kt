package com.bohdawn.reelix.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bohdawn.reelix.models.Movie
import com.bohdawn.reelix.models.MovieResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val httpClient: HttpClient) : ViewModel() {

    private val _moviesList = MutableStateFlow<List<Movie>>(emptyList())
    val moviesList: StateFlow<List<Movie>> = _moviesList.asStateFlow()

    private val _selectedMovie = MutableStateFlow<Movie?>(null)
    val selectedMovie: StateFlow<Movie?> = _selectedMovie.asStateFlow()

    private var currentPage = 1
    private var isLoading = false

    init {
        loadMovies()
    }

    fun loadMovies() {
        if (isLoading) return
        isLoading = true

        viewModelScope.launch {
            try {
                val response: MovieResponse = httpClient.get("movie/popular") {
                    parameter("page", currentPage)
                }.body()

                val currentMovies = _moviesList.value
                _moviesList.value = currentMovies + response.results

                currentPage++

            } catch (e: Exception) {
                println("Помилка завантаження: ${e.message}")
            } finally {
                isLoading = false
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