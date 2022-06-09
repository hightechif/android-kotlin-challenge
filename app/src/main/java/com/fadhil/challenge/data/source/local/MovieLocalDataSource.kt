package com.fadhil.challenge.data.source.local

import android.content.SharedPreferences
import com.fadhil.challenge.domain.model.Movie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class MovieLocalDataSource(
    sharedPreferences: SharedPreferences
) : LocalDataSource<List<Movie>>(sharedPreferences) {

    override fun getKeyName() = "movieList"
    override fun getValue(json: String): List<Movie> = Gson().fromJson(json, object : TypeToken<List<Movie>>() {}.type)

}