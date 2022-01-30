package com.fadhil.challenge.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fadhil.challenge.constant.Gender
import com.fadhil.challenge.data.source.local.entity.Student
import com.fadhil.challenge.data.source.local.room.StudentDao
import com.fadhil.challenge.model.StudentDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class StudentViewModel(private val studentDao: StudentDao) : ViewModel() {

    private fun insertStudent(student: Student) {
        viewModelScope.launch {
            studentDao.insert(student)
        }
    }

    fun allStudents(): Flow<List<Student>> = studentDao.getAll()

    fun getStudentById(id: Int): Flow<Student> = studentDao.getStudentById(id)

    fun updateStudent(id: Int,name: String, gender: Gender, gpa: Float) {
        val updatedStudent = StudentDto(id, name, gender, gpa)
        viewModelScope.launch {
            studentDao.update(updatedStudent)
        }
    }

    suspend fun deleteOne(student: Student) = studentDao.delete(student)

    suspend fun deleteAll() = studentDao.deleteAll()

    private fun getNewStudentEntry(name: String, gender: Gender, gpa: Float): Student {
        return Student(
            name = name,
            gender = gender,
            gpa = gpa
        )
    }

    fun addNewStudent(name: String, gender: Gender, gpa: Float) {
        val newStudent = getNewStudentEntry(name, gender, gpa)
        insertStudent(newStudent)
    }

    fun isEntryValid(name: String, gender: String, gpa: String): Boolean {
        return !(name.isBlank() || gender.isBlank() || gpa.isBlank())
    }

}