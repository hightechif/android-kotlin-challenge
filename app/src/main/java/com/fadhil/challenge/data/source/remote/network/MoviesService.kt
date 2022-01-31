package com.fadhil.challenge.data.source.remote.network

import com.fadhil.challenge.data.source.remote.response.BaseResponse
import com.fadhil.challenge.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET("/3/discover/movie")
    fun getMovieList(@Query("page") page: Int, @Query("api_key") apiKey: String): Call<BaseResponse<Movie>>

}