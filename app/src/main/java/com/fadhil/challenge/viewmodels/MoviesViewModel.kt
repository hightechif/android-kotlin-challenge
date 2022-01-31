package com.fadhil.challenge.viewmodels

import androidx.lifecycle.ViewModel
import com.fadhil.challenge.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject
constructor(private val movieRepository: MovieRepository) : ViewModel() {

    companion object {
        var page: Int = 1
    }

    val movies = movieRepository.getMovies(page)

}