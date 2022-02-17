package com.fadhil.challenge.domain.usecase

import com.fadhil.challenge.data.Result
import com.fadhil.challenge.data.source.MovieRepository
import com.fadhil.challenge.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject
constructor(private val movieRepository: MovieRepository) : MovieUseCase {

    override fun getMovies(page: Int): Flow<Result<List<Movie>?>> = movieRepository.getMovies(page)

}