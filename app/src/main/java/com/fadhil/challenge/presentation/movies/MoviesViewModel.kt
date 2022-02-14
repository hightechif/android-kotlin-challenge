package com.fadhil.challenge.presentation.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.fadhil.challenge.data.Result
import com.fadhil.challenge.data.source.local.entity.MovieEntity
import com.fadhil.challenge.data.source.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject
constructor(private val movieRepository: MovieRepository) : ViewModel() {

    companion object {
        var page: Int = 1
    }

    private lateinit var movies: LiveData<Result<List<MovieEntity>>>

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    fun getMovies(): LiveData<Result<List<MovieEntity>>> {
        movies = fetchMovies()
        return movies
    }

    private fun fetchMovies(): LiveData<Result<List<MovieEntity>>> {
        // If any transformation is needed, this can be simply done by Transformations class ...
        return movieRepository.getMovies(page)
    }
}
