package com.fadhil.challenge.data.source.local

import com.fadhil.challenge.data.source.local.entity.StudentEntity
import com.fadhil.challenge.data.source.local.room.StudentDao
import com.fadhil.challenge.domain.model.Student
import com.fadhil.challenge.domain.model.StudentCreate
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StudentLocalDataSource @Inject
constructor(val studentDao: StudentDao) {

    fun getStudentsFlow(): Flow<List<StudentEntity>> = studentDao.getStudentsFlow()

    fun getSmartStudents(): Flow<List<StudentEntity>> = studentDao.getSmartStudents()

    fun getStudentById(id: Long): Flow<StudentEntity> = studentDao.getStudentById(id)

    suspend fun insertStudent(student: StudentCreate) = studentDao.insert(student)

    suspend fun updateStudent(student: Student) = studentDao.update(student)

    suspend fun deleteStudent(student: Student) = studentDao.delete(student)

    suspend fun deleteAllStudents() = studentDao.deleteAll()
}