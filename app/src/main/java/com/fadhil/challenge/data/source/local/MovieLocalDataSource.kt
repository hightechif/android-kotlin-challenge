package com.fadhil.challenge.data.source.local

import com.fadhil.challenge.data.source.local.entity.Movie
import com.fadhil.challenge.data.source.local.entity.Student
import com.fadhil.challenge.data.source.local.room.MoviesDao
import com.fadhil.challenge.data.source.local.room.StudentDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieLocalDataSource @Inject
constructor(private val moviesDao: MoviesDao) {
    fun getAllMovies() = moviesDao.getAllMovies()

    suspend fun insertAllMovies(movies: List<Movie>) = moviesDao.insertAll(movies)

}