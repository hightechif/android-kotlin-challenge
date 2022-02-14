package com.fadhil.challenge.data.source.local

import com.fadhil.challenge.data.source.local.entity.MovieEntity
import com.fadhil.challenge.data.source.local.room.MoviesDao
import com.fadhil.challenge.domain.model.Movie
import com.fadhil.challenge.utils.DataMapperMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieLocalDataSource @Inject
constructor(private val moviesDao: MoviesDao) {

    fun getAllMovies(): Flow<List<Movie>> =
        moviesDao.getAllMovies().map {
            DataMapperMovie.mapMoviesToDomain(it)
        }

    suspend fun insertAllMovies(movieEntities: List<MovieEntity>) = moviesDao.insertAll(movieEntities)

}