package com.fadhil.challenge.data.remote

import com.fadhil.challenge.data.remote.service.MoviesService
import javax.inject.Inject

class RemoteDataSource @Inject
constructor(private val moviesService: MoviesService) : BaseDataSource() {

    suspend fun getMovies(page: Int, apiKey: String) = getResult { moviesService.getMovies(page, apiKey) }

}