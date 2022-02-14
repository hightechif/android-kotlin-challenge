package com.fadhil.challenge.data.source.remote

import com.fadhil.challenge.data.source.remote.network.MoviesService
import javax.inject.Inject

class MovieRemoteDataSource @Inject
constructor(private val moviesService: MoviesService) : BaseDataSource() {

    suspend fun getMovies(page: Int, apiKey: String) = getResult { moviesService.getMovies(page, apiKey) }

}