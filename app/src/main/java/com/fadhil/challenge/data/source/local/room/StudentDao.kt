package com.fadhil.challenge.data.source.local.room

import androidx.room.*
import com.fadhil.challenge.data.source.local.entity.StudentEntity
import com.fadhil.challenge.domain.model.Student
import com.fadhil.challenge.domain.model.StudentCreate
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {

    @Query("SELECT * FROM student ORDER BY id ASC")
    fun getStudentsFlow(): Flow<List<StudentEntity>>

    @Query("SELECT * FROM student WHERE gpa >= 3.00 ORDER BY gpa DESC")
    fun getSmartStudents(): Flow<List<StudentEntity>>

    @Query("SELECT EXISTS(SELECT * FROM student WHERE id = :id)")
    fun isStudentExist(id: Long): Flow<Boolean>

    @Query("SELECT * FROM student WHERE id = :id")
    fun getStudentById(id: Long): Flow<StudentEntity>

    @Insert(entity = StudentEntity::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(student: StudentCreate)

    @Update(entity = StudentEntity::class)
    suspend fun update(student: Student)

    @Delete(entity = StudentEntity::class)
    suspend fun delete(student: StudentEntity)

    @Query("DELETE FROM student")
    suspend fun deleteAll()

}