package com.fadhil.challenge.data.source

import com.fadhil.challenge.data.source.local.StudentLocalDataSource
import com.fadhil.challenge.domain.model.Student
import com.fadhil.challenge.domain.model.StudentCreate
import com.fadhil.challenge.domain.repository.IStudentRepository
import com.fadhil.challenge.utils.DataMapperStudent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StudentRepository @Inject
constructor(private val studentLocalDataSource: StudentLocalDataSource) : IStudentRepository {

    override fun getStudentsFlow(): Flow<List<Student>?> {
        return studentLocalDataSource.getStudentsFlow().map {
            DataMapperStudent.mapStudentsToDomain(it)
        }
    }

    override fun getSmartStudents(): Flow<List<Student>?> =
        studentLocalDataSource.getSmartStudents().map {
            DataMapperStudent.mapStudentsToDomain(it)
        }

    override fun isStudentExist(id: Long): Flow<Boolean> = studentLocalDataSource.isStudentExist(id)

    override fun getStudentById(id: Long): Flow<Student?> {
        return studentLocalDataSource.getStudentById(id).map {
            DataMapperStudent.mapStudentToDomain(it)
        }
    }

    override suspend fun insert(student: StudentCreate) = studentLocalDataSource.insertStudent(student)

    override suspend fun update(student: Student) = studentLocalDataSource.updateStudent(student)

    override suspend fun delete(id: Long) = studentLocalDataSource.deleteStudent(id)

    override suspend fun deleteAll() = studentLocalDataSource.deleteAllStudents()

}