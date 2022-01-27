package com.fadhil.challenge.data.room.student

import androidx.room.*
import com.fadhil.challenge.model.StudentDto
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(student: Student)

    @Query("SELECT * FROM student ORDER BY id ASC")
    fun getAll(): Flow<List<Student>>

    @Query("SELECT * FROM student WHERE id = :id ORDER BY id ASC")
    fun getStudentById(id: Int): Flow<Student>

    @Query("SELECT * FROM student WHERE gpa >= 3.00 ORDER BY gpa DESC")
    fun getSmartStudents(): Flow<List<Student>>

    @Update(entity = Student::class)
    suspend fun update(studentDto: StudentDto)

    @Delete(entity = Student::class)
    suspend fun delete(student: Student)

    @Query("DELETE FROM student")
    suspend fun deleteAll()

}