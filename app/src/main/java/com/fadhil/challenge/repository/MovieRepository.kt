package com.fadhil.challenge.repository

import com.fadhil.challenge.model.BaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieRepository {

    @GET("/3/discover/movie")
    fun getMovieList(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): Call<BaseResponse>

}