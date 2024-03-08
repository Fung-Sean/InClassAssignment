package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

object RetrofitClient {
    private const val BASE_URL = "http://www.omdbapi.com/"

    private val moshi = Moshi.Builder().build()


    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private val apiCall = retrofit.create(MovieAPI::class.java)

}