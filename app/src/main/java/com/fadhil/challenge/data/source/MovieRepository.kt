package com.fadhil.challenge.data.source

import com.fadhil.challenge.data.NetworkBoundResource
import com.fadhil.challenge.data.Result
import com.fadhil.challenge.data.source.local.MovieLocalDataSource
import com.fadhil.challenge.data.source.local.SessionLocalDataSource
import com.fadhil.challenge.data.source.remote.MovieRemoteDataSource
import com.fadhil.challenge.data.source.remote.SessionRemoteDataSource
import com.fadhil.challenge.data.source.remote.response.MovieResponse
import com.fadhil.challenge.data.source.remote.response.base.PageableData
import com.fadhil.challenge.domain.model.Movie
import com.fadhil.challenge.domain.repository.IMovieRepository
import com.fadhil.challenge.utils.DataMapperMovie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepository @Inject
constructor(
    private val sessionRemoteDataSource: SessionRemoteDataSource,
    private val sessionLocalDataSource: SessionLocalDataSource,
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieLocalDataSource: MovieLocalDataSource
) : IMovieRepository {

    override fun getMovies(page: Int): Flow<Result<List<Movie>?>> =
        object : NetworkBoundResource<List<Movie>?, PageableData<MovieResponse>>(
            sessionLocalDataSource, sessionRemoteDataSource
        ) {
            override fun loadFromDB(): List<Movie>? =
                movieLocalDataSource.getCached()

            override fun shouldFetch(data: List<Movie>?) = data.isNullOrEmpty()

            override suspend fun createCall(): Result<PageableData<MovieResponse>> {
                    return movieRemoteDataSource.getMovies(page)
            }

            override suspend fun saveCallResult(data: PageableData<MovieResponse>) {
                if (data.totalResults > 0) {
                    val movies = DataMapperMovie.mapMoviesToDomain(data.results)
                    movieLocalDataSource.save(movies)
                }
            }

        }.asFlow()

}