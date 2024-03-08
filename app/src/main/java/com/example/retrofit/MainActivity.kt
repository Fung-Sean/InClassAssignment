package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory


const val TAG = "sussy"
class MainActivity : AppCompatActivity() {
    private lateinit var movieAPI: MovieAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieAPI = RetrofitClient.movieAPI

        findViewById<Button>(R.id.submitButton).setOnClickListener {
            val movieTitle = findViewById<EditText>(R.id.movieInputEditText).text.toString()
            if (movieTitle.isNotEmpty()) {
                Log.d(TAG, "movie title" + movieTitle)
                searchMovie(movieTitle)
            }
        }
    }

    private fun searchMovie(title: String) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                Log.d(TAG, "TRYING API")
                // Make API call to search for movies based on the title
                val movieResponse = movieAPI.searchMovies(title)

                // Get the first movie from the search results
                val firstMovie = movieResponse.search.firstOrNull()

                // Handle the first movie
                if (firstMovie != null) {
                    Log.d("sussy","Title: ${firstMovie.title}, Year: ${firstMovie.year}, Url: ${firstMovie.poster}\")")
                } else {
                    Log.d("sussy", "No movies found")
                }
            } catch (e: Exception) {
                // Handle errors
                println("Error: ${e.message}")
            }
        }
    }
}

object RetrofitClient {
    private const val BASE_URL = "http://www.omdbapi.com/"

    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val movieAPI = retrofit.create(MovieAPI::class.java)
}