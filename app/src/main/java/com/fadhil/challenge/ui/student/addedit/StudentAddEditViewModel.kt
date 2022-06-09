package com.fadhil.challenge.ui.student.addedit

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.fadhil.challenge.constant.Gender
import com.fadhil.challenge.domain.model.Student
import com.fadhil.challenge.domain.model.StudentCreate
import com.fadhil.challenge.domain.usecase.StudentInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentAddEditViewModel @Inject
constructor(private val studentInteractor: StudentInteractor) : ViewModel() {

    private lateinit var student: LiveData<Student?>

    fun isStudentExist(id: Long): LiveData<Boolean> {
        return studentInteractor.isStudentExist(id).asLiveData()
    }

    fun getStudent(id: Long): LiveData<Student?> {
        student = loadStudent(id)
        return student
    }

    private fun loadStudent(id: Long): LiveData<Student?> {
        return studentInteractor.getStudentById(id).asLiveData()
    }

    private fun insertStudent(student: StudentCreate) {
        viewModelScope.launch {
            studentInteractor.insert(student)
        }
    }

    fun updateStudent(id: Long, name: String, gender: Gender, gpa: Float) {
        val updatedStudent = Student(id, name, gender, gpa)
        viewModelScope.launch {
            studentInteractor.update(updatedStudent)
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