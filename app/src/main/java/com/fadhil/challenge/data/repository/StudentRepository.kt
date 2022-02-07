package com.fadhil.challenge.data.repository

import com.fadhil.challenge.data.entities.Student
import com.fadhil.challenge.data.local.LocalDataSource
import com.fadhil.challenge.data.remote.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StudentRepository @Inject
constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {

    val studentsFlow: Flow<List<Student>>
        get() = localDataSource.getStudentsFlow()

    fun getSmartStudents(): Flow<List<Student>> = localDataSource.getSmartStudents()

    fun getStudentById(id: Int): Flow<Student> = localDataSource.getStudentById(id)

    suspend fun insert(student: Student) = localDataSource.insertStudent(student)

    suspend fun update(student: Student) = localDataSource.updateStudent(student)

    suspend fun delete(student: Student) = localDataSource.deleteStudent(student)

    suspend fun deleteAll() = localDataSource.deleteAllStudents()

}