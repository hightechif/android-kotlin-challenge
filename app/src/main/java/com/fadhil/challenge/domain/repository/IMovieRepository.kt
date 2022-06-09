package com.fadhil.challenge.domain.repository

import com.fadhil.challenge.data.Result
import com.fadhil.challenge.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getMovies(page: Int): Flow<Result<List<Movie>?>>

}