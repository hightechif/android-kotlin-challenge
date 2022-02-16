package com.fadhil.challenge.data.source.remote.network

import com.fadhil.challenge.data.source.remote.response.base.PageableData
import com.fadhil.challenge.data.source.local.entity.MovieEntity
import com.fadhil.challenge.data.source.remote.response.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET("/3/discover/movie")
    suspend fun getMovies(@Query("page") page: Int, @Query("api_key") apiKey: String): Response<PageableData<MovieResponse>>

}