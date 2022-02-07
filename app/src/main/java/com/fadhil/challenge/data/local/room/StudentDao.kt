package com.fadhil.challenge.data.local.room

import androidx.room.*
import com.fadhil.challenge.data.entities.Student
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {

    @Query("SELECT * FROM student ORDER BY id ASC")
    fun getStudentsFlow(): Flow<List<Student>>

    @Query("SELECT * FROM student WHERE gpa >= 3.00 ORDER BY gpa DESC")
    fun getSmartStudents(): Flow<List<Student>>

    @Query("SELECT * FROM student WHERE id = :id")
    fun getStudentById(id: Int): Flow<Student>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(student: Student)

    @Update(entity = Student::class)
    suspend fun update(student: Student)

    @Delete(entity = Student::class)
    suspend fun delete(student: Student)

    @Query("DELETE FROM student")
    suspend fun deleteAll()

}