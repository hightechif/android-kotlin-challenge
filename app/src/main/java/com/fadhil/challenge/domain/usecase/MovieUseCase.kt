package com.fadhil.challenge.domain.usecase

import com.fadhil.challenge.data.Result
import com.fadhil.challenge.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    fun getMovies(page: Int): Flow<Result<List<Movie>?>>

}