package com.fadhil.challenge.repository

import com.fadhil.challenge.model.SampleApi
import retrofit2.Call
import retrofit2.http.GET

interface SampleApiRepository {
    @GET("/posts")
    fun getPosts(): Call<ArrayList<SampleApi>>
}