package com.fadhil.challenge.data.source

import com.fadhil.challenge.data.Result
import com.fadhil.challenge.data.source.local.entity.MovieEntity
import com.fadhil.challenge.data.source.local.MovieLocalDataSource
import com.fadhil.challenge.data.source.remote.MovieRemoteDataSource
import com.fadhil.challenge.data.source.remote.response.base.PageableData
import javax.inject.Inject

class MovieRepository @Inject
constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource
) {

    companion object {
        const val API_KEY = "31bcf72a6584461df2daa00c58f75514"
    }

    suspend fun getMovies(page: Int): Result<PageableData<MovieEntity>> =
        movieRemoteDataSource.getMovies(page, API_KEY)

}