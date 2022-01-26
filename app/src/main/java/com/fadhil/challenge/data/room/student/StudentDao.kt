package com.fadhil.challenge.data.room.student

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {

    @Query("SELECT * FROM student ORDER BY id ASC")
    fun getAll(): Flow<List<Student>>

}