package com.bohdawn.reelix.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val id: Int,
    val title: String,
    val overview: String,

    @SerialName("poster_path")
    val posterPath: String? = null,

    @SerialName("release_date")
    val releaseDate: String? = null,

    @SerialName("vote_average")
    val voteAverage: Double = 0.0
) {
    val fullPosterUrl: String
        get() = if (posterPath != null) "https://image.tmdb.org/t/p/w500$posterPath" else ""
}