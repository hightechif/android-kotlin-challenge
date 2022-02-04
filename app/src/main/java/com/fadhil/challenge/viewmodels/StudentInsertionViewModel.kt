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
class StudentInsertionViewModel @Inject
constructor(private val studentRepository: StudentRepository) : ViewModel() {

    private fun insertStudent(student: Student) {
        viewModelScope.launch {
            studentRepository.insert(student)
        }
    }

    fun addNewStudent(name: String, gender: Gender, gpa: Float) {
        val newStudent = getNewStudentEntry(name, gender, gpa)
        insertStudent(newStudent)
    }

    private fun getNewStudentEntry(name: String, gender: Gender, gpa: Float): Student {
        return Student(
            name = name,
            gender = gender,
            gpa = gpa
        )
    }

    fun isEntryValid(name: String, gender: String, gpa: String): Boolean {
        return !(name.isBlank() || gender.isBlank() || gpa.isBlank())
    }

}