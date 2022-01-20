package com.fadhil.challenge.retrofit

import com.fadhil.challenge.repository.MovieRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieRetrofitClient {

    private const val BASE_URL = "https://api.themoviedb.org"

    val instance: MovieRepository by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(MovieRepository::class.java)
    }
}