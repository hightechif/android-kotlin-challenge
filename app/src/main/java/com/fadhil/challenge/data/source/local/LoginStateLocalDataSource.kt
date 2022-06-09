package com.fadhil.challenge.data.source.local

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LoginStateLocalDataSource(
    sharedPreferences: SharedPreferences
) : LocalDataSource<Boolean>(sharedPreferences) {
    override fun getKeyName() = "loginState"
    override fun getValue(json: String): Boolean =
        Gson().fromJson(json, object : TypeToken<Boolean>() {}.type)

}