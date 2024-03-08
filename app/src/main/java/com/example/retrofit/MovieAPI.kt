package com.example.retrofit

import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "2ec4a253"
interface MovieAPI {
    @GET("/?apikey=$API_KEY")
    suspend fun searchMovies(@Query("s") title: String): MovieResponse
}