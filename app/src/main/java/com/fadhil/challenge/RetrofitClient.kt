package com.fadhil.challenge

import com.fadhil.challenge.repository.SampleApiRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://jsonplaceholder.typicode.com"

    val instance: SampleApiRepository by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(SampleApiRepository::class.java)
    }
}