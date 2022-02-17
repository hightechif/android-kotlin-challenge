package com.fadhil.challenge.presentation.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fadhil.challenge.data.Result
import com.fadhil.challenge.data.source.MovieRepository
import com.fadhil.challenge.domain.model.Movie
import com.fadhil.challenge.domain.usecase.MovieInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject
constructor(private val movieInteractor: MovieInteractor) : ViewModel() {

    companion object {
        var page: Int = 1
    }

    private lateinit var movies: LiveData<Result<List<Movie>?>>

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    fun getMovies(): LiveData<Result<List<Movie>?>> {
        movies = fetchMovies()
        return movies
    }

    private fun fetchMovies(): LiveData<Result<List<Movie>?>> {
        // If any transformation is needed, this can be simply done by Transformations class ...
        return movieInteractor.getMovies(page).asLiveData()
    }
}
