package com.fadhil.challenge.data.source.local

import android.content.SharedPreferences
import com.fadhil.challenge.domain.model.Session
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SessionLocalDataSource(
    sharedPreferences: SharedPreferences
) : LocalDataSource<Session>(sharedPreferences) {
    override fun getKeyName() = "session"
    override fun getValue(json: String): Session =
        Gson().fromJson(json, object : TypeToken<Session>() {}.type)
}