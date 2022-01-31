package com.fadhil.challenge.data.source.remote

import com.fadhil.challenge.data.source.remote.network.MoviesService

class RemoteDataSource(private val moviesService: MoviesService) {

    fun getMovieList(page: Int, apiKey: String) = moviesService.getMovieList(page, apiKey)

}
