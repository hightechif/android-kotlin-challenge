package com.fadhil.challenge.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fadhil.challenge.data.source.MovieRepository
import com.fadhil.challenge.model.Movie


class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val movieListObservable: MutableLiveData<List<Movie>> by lazy {
        MutableLiveData<List<Movie>>().also {
            loadMovies()
        }
    }

    companion object {
        var page: Int = 1
        const val API_KEY: String = "31bcf72a6584461df2daa00c58f75514"
    }

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    fun getMovies(): LiveData<List<Movie>> {
        return movieListObservable
    }

    private fun loadMovies(): MutableLiveData<List<Movie>> {
        // If any transformation is needed, this can be simply done by Transformations class ...
        return movieRepository.getMovieList(page, API_KEY)
    }

}