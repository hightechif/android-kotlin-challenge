package com.fadhil.challenge.domain.usecase

import com.fadhil.challenge.domain.model.Student
import com.fadhil.challenge.domain.model.StudentCreate
import kotlinx.coroutines.flow.Flow

interface StudentUseCase {

    fun getStudentsFlow(): Flow<List<Student>?>

    fun getSmartStudents(): Flow<List<Student>?>

    fun isStudentExist(id: Long): Flow<Boolean>

    fun getStudentById(id: Long): Flow<Student?>

    suspend fun insert(student: StudentCreate)

    suspend fun update(student: Student)

    suspend fun delete(id: Long)

    suspend fun deleteAll()

}