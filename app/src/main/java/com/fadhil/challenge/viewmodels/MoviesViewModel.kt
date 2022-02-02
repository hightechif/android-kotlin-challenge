package com.fadhil.challenge.viewmodels

import androidx.lifecycle.*
import com.fadhil.challenge.data.Resource
import com.fadhil.challenge.data.entities.Movie
import com.fadhil.challenge.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject
constructor(private val movieRepository: MovieRepository) : ViewModel() {

    companion object {
        var page: Int = 1
    }

    private lateinit var movies: LiveData<Resource<List<Movie>>>

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    fun getMovies(): LiveData<Resource<List<Movie>>> {
        movies = fetchMovies()
        return movies
    }

    private fun fetchMovies(): LiveData<Resource<List<Movie>>> {
        // If any transformation is needed, this can be simply done by Transformations class ...
        return movieRepository.getMovies(page)
    }
}
