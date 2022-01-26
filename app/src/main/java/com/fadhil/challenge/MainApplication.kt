package com.fadhil.challenge

import android.app.Application
import com.fadhil.challenge.data.room.AppDatabase
import timber.log.Timber

class MainApplication: Application() {

    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

}