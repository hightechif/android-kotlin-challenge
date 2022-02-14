package com.fadhil.challenge.data.source.local

import com.fadhil.challenge.data.source.local.entity.Student
import com.fadhil.challenge.data.source.local.room.StudentDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StudentLocalDataSource @Inject
constructor(val studentDao: StudentDao) {

    fun getStudentsFlow(): Flow<List<Student>> = studentDao.getStudentsFlow()

    fun getSmartStudents(): Flow<List<Student>> = studentDao.getSmartStudents()

    fun getStudentById(id: Int): Flow<Student> = studentDao.getStudentById(id)

    suspend fun insertStudent(student: Student) = studentDao.insert(student)

    suspend fun updateStudent(student: Student) = studentDao.update(student)

    suspend fun deleteStudent(student: Student) = studentDao.delete(student)

    suspend fun deleteAllStudents() = studentDao.deleteAll()
}