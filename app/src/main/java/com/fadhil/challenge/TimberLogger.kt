package com.fadhil.challenge

import android.app.Application
import timber.log.Timber

class TimberLogger: Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}