package com.fadhil.challenge.data.remote.service

import com.fadhil.challenge.data.remote.response.BaseResponse
import com.fadhil.challenge.data.entities.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET("/3/discover/movie")
    suspend fun getMovies(@Query("page") page: Int, @Query("api_key") apiKey: String): Response<BaseResponse<Movie>>

}