package com.fadhil.challenge.retrofit

import com.fadhil.challenge.service.MovieService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieRetrofitClient {

    private const val BASE_URL = "https://api.themoviedb.org"

    val instance: MovieService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(MovieService::class.java)
    }
}