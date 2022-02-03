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

    suspend fun insert(student: Student) = localDataSource.insertStudent(student)

    fun getAll(): Flow<List<Student>> = localDataSource.getAllStudents()

    fun getStudentById(id: Int): Flow<Student> = localDataSource.getStudentById(id)

    fun getSmartStudents(): Flow<List<Student>> = localDataSource.getSmartStudents()

    suspend fun update(student: Student) = localDataSource.updateStudent(student)

    suspend fun delete(student: Student) = localDataSource.deleteStudent(student)

    suspend fun deleteAll() = localDataSource.deleteAllStudents()

}