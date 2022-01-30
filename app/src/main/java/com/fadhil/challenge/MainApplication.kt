package com.fadhil.challenge

import android.app.Application
import com.fadhil.challenge.data.source.local.room.StudentDatabase
import timber.log.Timber

class MainApplication: Application() {

    val database: StudentDatabase by lazy { StudentDatabase.getDatabase(this) }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

}