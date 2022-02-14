package com.fadhil.challenge.data.source

import androidx.lifecycle.LiveData
import com.fadhil.challenge.data.Result
import com.fadhil.challenge.data.source.local.entity.Movie
import com.fadhil.challenge.data.source.local.MovieLocalDataSource
import com.fadhil.challenge.data.source.remote.MovieRemoteDataSource
import com.fadhil.challenge.utils.DataAccessStrategy
import javax.inject.Inject

class MovieRepository @Inject
constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource
) {

    companion object {
        const val API_KEY = "31bcf72a6584461df2daa00c58f75514"
    }

    fun getMovies(page: Int): LiveData<Result<List<Movie>>> = DataAccessStrategy.performGetOperation(
        databaseQuery = { movieLocalDataSource.getAllMovies() },
        networkCall = { movieRemoteDataSource.getMovies(page, API_KEY) },
        saveCallResult = { movieLocalDataSource.insertAllMovies(it.results) }
    )

}