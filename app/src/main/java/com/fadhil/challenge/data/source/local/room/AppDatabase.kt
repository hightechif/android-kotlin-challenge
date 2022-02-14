package com.fadhil.challenge.data.source.local.room

import androidx.room.Database
//import android.content.Context
//import androidx.room.Room
import androidx.room.RoomDatabase
import com.fadhil.challenge.data.source.local.entity.MovieEntity
import com.fadhil.challenge.data.source.local.entity.StudentEntity

@Database(entities = [StudentEntity::class, MovieEntity::class], version = 3, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    /**
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        private const val DATABASE_NAME = "app_database"

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
    */

    abstract fun studentDao(): StudentDao

    abstract fun moviesDao(): MoviesDao

}