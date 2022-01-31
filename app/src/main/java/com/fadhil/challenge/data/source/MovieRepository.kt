package com.fadhil.challenge.data.source

import androidx.lifecycle.MutableLiveData
import com.fadhil.challenge.data.source.local.LocalDataSource
import com.fadhil.challenge.data.source.remote.RemoteDataSource
import com.fadhil.challenge.data.source.remote.response.BaseResponse
import com.fadhil.challenge.model.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class MovieRepository(
    private val remoteDataSource: RemoteDataSource
) {

    fun getMovieList(page: Int, apiKey: String): MutableLiveData<List<Movie>> {
        lateinit var data: MutableLiveData<List<Movie>>
        remoteDataSource.getMovieList(page, apiKey)
            .enqueue(object : Callback<BaseResponse<Movie>> {
                override fun onResponse(
                    call: Call<BaseResponse<Movie>>,
                    response: Response<BaseResponse<Movie>>
                ) {
                    val responseBody = response.body()
                    Timber.i("HTTP Response: $response")
                    responseBody?.results?.let { data.value = it }
                }

                override fun onFailure(call: Call<BaseResponse<Movie>>, t: Throwable) {

                }
            })
        return data
    }


}