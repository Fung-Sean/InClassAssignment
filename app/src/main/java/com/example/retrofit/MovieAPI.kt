package com.example.retrofit

import retrofit2.http.GET

private const val API_KEY = "2ec4a253"
interface MovieAPI {
    @GET("/?i=tt3896198&apikey=$API_KEY")
    suspend fun getMovieDetails(): MovieResponse
}