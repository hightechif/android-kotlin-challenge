package com.fadhil.challenge.data.source.remote

import com.fadhil.challenge.data.source.remote.network.MoviesApiService
import com.fadhil.challenge.data.source.remote.response.BaseResponse
import com.fadhil.challenge.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class RemoteDataSource(
    private val moviesApiService: MoviesApiService
) {

    suspend fun getMovieList(page: Int, apiKey: String) =
        withContext(Dispatchers.IO) {
            moviesApiService.getMovieList(page, apiKey)
        }
}

interface MoviesApi {
    // Makes movie network synchronous requests.
    fun fetchMovieList(): Flow<BaseResponse<Movie>>
}
