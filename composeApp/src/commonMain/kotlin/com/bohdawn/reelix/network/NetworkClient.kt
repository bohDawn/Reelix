package com.bohdawn.reelix.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType.Application.Json
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object NetworkClient {
    private const val API_KEY = TMDB_API_KEY
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }

        defaultRequest {
            url(BASE_URL)
            url {
                parameters.append("api_key", API_KEY)
            }
        }
    }
}