package com.fadhil.challenge.domain.usecase

import com.fadhil.challenge.data.source.StudentRepository
import com.fadhil.challenge.domain.model.Student
import com.fadhil.challenge.domain.model.StudentCreate
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StudentInteractor @Inject
constructor(private val studentRepository: StudentRepository) : StudentUseCase {

    override fun getStudentsFlow(): Flow<List<Student>?> = studentRepository.getStudentsFlow()

    override fun getSmartStudents(): Flow<List<Student>?> = studentRepository.getSmartStudents()

    override fun isStudentExist(id: Long): Flow<Boolean> = studentRepository.isStudentExist(id)

    override fun getStudentById(id: Long): Flow<Student?> = studentRepository.getStudentById(id)

    override suspend fun insert(student: StudentCreate) = studentRepository.insert(student)

    override suspend fun update(student: Student) = studentRepository.update(student)

    override suspend fun delete(id: Long) = studentRepository.delete(id)

    override suspend fun deleteAll() = studentRepository.deleteAll()

}