package com.fadhil.challenge.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.fadhil.challenge.data.source.MovieRepository
import com.fadhil.challenge.data.source.remote.response.BaseResponse
import com.fadhil.challenge.model.Movie
import kotlinx.coroutines.Dispatchers
import retrofit2.Call

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    val movieList : LiveData<Call<BaseResponse<Movie>>> = liveData(Dispatchers.IO) {
        loading.postValue(true)
        emit(movieRepository.getMovieList(1, ""))
    }

    private val loading = MutableLiveData<Boolean>()
}