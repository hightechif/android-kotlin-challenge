package com.fadhil.challenge.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fadhil.challenge.constant.Gender
import com.fadhil.challenge.data.room.student.Student
import com.fadhil.challenge.data.room.student.StudentDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class StudentViewModel(private val studentDao: StudentDao) : ViewModel() {

    private fun insertStudent(student: Student) {
        viewModelScope.launch {
            studentDao.insert(student)
        }
    }

    fun allStudents(): Flow<List<Student>> = studentDao.getAll()

    fun updateStudent(name: String, gender: Gender, gpa: Float) {
        val updatedStudent = getNewStudentEntry(name, gender, gpa)
        viewModelScope.launch {
            studentDao.update(updatedStudent)
        }
    }

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