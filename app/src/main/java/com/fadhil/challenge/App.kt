package com.fadhil.challenge

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.bumptech.glide.annotation.GlideModule
import com.fadhil.challenge.data.local.room.AppDatabase
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App: Application() {

    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) // Ignore Night Mode
    }

}