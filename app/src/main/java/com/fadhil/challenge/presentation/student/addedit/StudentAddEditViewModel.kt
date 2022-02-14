package com.fadhil.challenge.presentation.student.addedit

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.fadhil.challenge.constant.Gender
import com.fadhil.challenge.data.source.StudentRepository
import com.fadhil.challenge.domain.model.Student
import com.fadhil.challenge.domain.model.StudentCreate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentAddEditViewModel @Inject
constructor(private val studentRepository: StudentRepository) : ViewModel() {

    private lateinit var student: LiveData<Student>

    fun getStudent(id: Long): LiveData<Student> {
        student = studentRepository.getStudentById(id).asLiveData()
        return student
    }

    private fun insertStudent(student: StudentCreate) {
        viewModelScope.launch {
            studentRepository.insert(student)
        }
    }

    fun updateStudent(id: Long, name: String, gender: Gender, gpa: Float) {
        val updatedStudent = Student(id, name, gender, gpa)
        viewModelScope.launch {
            studentRepository.update(updatedStudent)
        }
    }

    fun addNewStudent(name: String, gender: Gender, gpa: Float) {
        val newStudent = getNewStudentEntry(name, gender, gpa)
        insertStudent(newStudent)
    }

    private fun getNewStudentEntry(name: String, gender: Gender, gpa: Float): StudentCreate {
        return StudentCreate(
            name = name,
            gender = gender,
            gpa = gpa
        )
    }

    fun isEntryValid(name: String, gender: String, gpa: String): Boolean {
        return !(name.isBlank() || gender.isBlank() || gpa.isBlank())
    }

}