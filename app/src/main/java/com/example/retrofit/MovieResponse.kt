package com.example.retrofit

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieResponse(

    val Title: String,
    val Year: String,
    @Json(name = "Poster") val Poster: String,
)
