package com.fadhil.challenge.data.source

import com.fadhil.challenge.data.source.local.StudentLocalDataSource
import com.fadhil.challenge.data.source.local.entity.Student
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StudentRepository @Inject
constructor(private val studentLocalDataSource: StudentLocalDataSource) {

    val studentsFlow: Flow<List<Student>>
        get() = studentLocalDataSource.getStudentsFlow()

    fun getSmartStudents(): Flow<List<Student>> = studentLocalDataSource.getSmartStudents()

    fun getStudentById(id: Int): Flow<Student> = studentLocalDataSource.getStudentById(id)

    suspend fun insert(student: Student) = studentLocalDataSource.insertStudent(student)

    suspend fun update(student: Student) = studentLocalDataSource.updateStudent(student)

    suspend fun delete(student: Student) = studentLocalDataSource.deleteStudent(student)

    suspend fun deleteAll() = studentLocalDataSource.deleteAllStudents()

}