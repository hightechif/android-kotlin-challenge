package com.fadhil.challenge.data.source.remote.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MoviesApi {

    private const val BASE_URL = "https://api.themoviedb.org"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val instance: MoviesApiService by lazy {
        retrofit.create(MoviesApiService::class.java)
    }
}