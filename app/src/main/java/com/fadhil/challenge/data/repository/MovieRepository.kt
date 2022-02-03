package com.fadhil.challenge.data.repository

import androidx.lifecycle.LiveData
import com.fadhil.challenge.data.Resource
import com.fadhil.challenge.data.entities.Movie
import com.fadhil.challenge.data.local.LocalDataSource
import com.fadhil.challenge.data.remote.RemoteDataSource
import com.fadhil.challenge.utils.DataAccessStrategy
import javax.inject.Inject

class MovieRepository @Inject
constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {

    companion object {
        const val API_KEY = "31bcf72a6584461df2daa00c58f75514"
    }

    fun getMovies(page: Int): LiveData<Resource<List<Movie>>> = DataAccessStrategy.performGetOperation(
        databaseQuery = { localDataSource.getAllMovies() },
        networkCall = { remoteDataSource.getMovies(page, API_KEY) },
        saveCallResult = { localDataSource.insertAllMovies(it.results) }
    )

}