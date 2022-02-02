package com.fadhil.challenge.data.local

import com.fadhil.challenge.data.entities.Movie
import com.fadhil.challenge.data.local.room.MoviesDao
import javax.inject.Inject

class LocalDataSource @Inject
constructor(private val moviesDao: MoviesDao) {

    fun getAllMovies() = moviesDao.getAllMovies()

    suspend fun insertAllMovies(movies: List<Movie>) = moviesDao.insertAll(movies)

}