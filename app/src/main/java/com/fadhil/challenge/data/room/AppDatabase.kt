package com.fadhil.challenge.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fadhil.challenge.data.room.student.Student
import com.fadhil.challenge.data.room.student.StudentDao

@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

    abstract fun studentDao(): StudentDao

}