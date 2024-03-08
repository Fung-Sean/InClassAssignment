package com.example.retrofit

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieResponse(

    @Json(name = "Title") val title: String,
    @Json(name = "Year") val year: String,
    @Json(name = "Poster") val Poster: String,
)
