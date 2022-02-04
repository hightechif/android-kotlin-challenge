package com.fadhil.challenge.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fadhil.challenge.constant.Gender
import com.fadhil.challenge.data.entities.Student
import com.fadhil.challenge.data.repository.StudentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentEditViewModel @Inject
constructor(private val studentRepository: StudentRepository) : ViewModel() {

    var student: Student? = null

    fun getStudent(id: Int): Student {
        viewModelScope.launch {
            student = studentRepository.getStudentById(id)
        }
        return student!!
    }

    fun updateStudent(id: Int, name: String, gender: Gender, gpa: Float) {
        val updatedStudent = Student(id, name, gender, gpa)
        viewModelScope.launch {
            studentRepository.update(updatedStudent)
        }
    }

    fun isEntryValid(name: String, gender: String, gpa: String): Boolean {
        return !(name.isBlank() || gender.isBlank() || gpa.isBlank())
    }
}