package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var movieAPI: MovieAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieAPI = RetrofitClient.apiCall

        findViewById<Button>(R.id.submitButton).setOnClickListener {
            val movieTitle = findViewById<EditText>(R.id.movieInputEditText).text.toString()
            if (movieTitle.isNotEmpty()) {
                searchMovie(movieTitle)
            }
        }
    }

    private fun searchMovie(title: String) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                // Make API call with the provided movie title
                val movieResponse = movieAPI.getMovieDetails(title)

                // Handle the movie response
                println("Title: ${movieResponse.title}, Year: ${movieResponse.year}")
            } catch (e: Exception) {
                // Handle errors
                println("Error: ${e.message}")
            }
        }
    }
}

object RetrofitClient {
    private const val BASE_URL = "http://www.omdbapi.com/"

    private val moshi = Moshi.Builder().build()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val apiCall = retrofit.create(MovieAPI::class.java)
}