package com.fadhil.challenge.data.source

import com.fadhil.challenge.data.source.local.LocalDataSource
import com.fadhil.challenge.data.source.remote.RemoteDataSource
import com.fadhil.challenge.data.source.remote.response.BaseResponse
import com.fadhil.challenge.model.Movie
import retrofit2.Call

interface MovieRepository {

    suspend fun getMovieList(page: Int, apiKey: String) : Call<BaseResponse<Movie>>

}

class MovieRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : MovieRepository {

    override suspend fun getMovieList(page: Int, apiKey: String) = remoteDataSource.getMovieList(page, apiKey)

}