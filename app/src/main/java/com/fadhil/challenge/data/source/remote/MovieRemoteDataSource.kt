package com.fadhil.challenge.data.source.remote

import com.fadhil.challenge.data.source.remote.network.MoviesService
import javax.inject.Inject

class MovieRemoteDataSource @Inject
constructor(private val moviesService: MoviesService) : BaseDataSource() {

    companion object {
        const val API_KEY = "31bcf72a6584461df2daa00c58f75514"
    }

    suspend fun getMovies(page: Int) = getResult { moviesService.getMovies(page, API_KEY) }

}