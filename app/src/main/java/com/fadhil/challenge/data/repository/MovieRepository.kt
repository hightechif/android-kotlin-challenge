package com.fadhil.challenge.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.fadhil.challenge.data.Resource
import com.fadhil.challenge.data.entities.Movie
import com.fadhil.challenge.data.local.room.MoviesDao
import com.fadhil.challenge.data.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: MoviesDao
) {

    companion object {
        const val API_KEY = "31bcf72a6584461df2daa00c58f75514"
    }

    fun getMovies(page: Int): LiveData<Resource<List<Movie>>> = performGetOperation(
        databaseQuery = { localDataSource.getAllMovies() },
        networkCall = { remoteDataSource.getMovies(page, API_KEY) },
        saveCallResult = { localDataSource.insertAll(it.results) }
    )

    /**
     * Data Access Strategy
     * */
    private fun <T, A> performGetOperation(databaseQuery: () -> LiveData<T>,
                                           networkCall: suspend () -> Resource<A>,
                                           saveCallResult: suspend (A) -> Unit): LiveData<Resource<T>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val source = databaseQuery.invoke().map { Resource.success(it) }
            emitSource(source)

            val responseStatus = networkCall.invoke()
            if (responseStatus.status == Resource.Status.SUCCESS) {
                saveCallResult(responseStatus.data!!)

            } else if (responseStatus.status == Resource.Status.ERROR) {
                emit(Resource.error(responseStatus.message!!))
                emitSource(source)
            }
        }

}