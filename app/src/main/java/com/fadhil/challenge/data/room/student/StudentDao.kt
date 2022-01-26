package com.fadhil.challenge.data.room.student

import androidx.room.Dao
import androidx.room.Query

@Dao
interface StudentDao {

    @Query("SELECT * FROM student ORDER BY id ASC")
    fun getAll(): List<Student>

}