package com.fadhil.challenge.viewmodels

import androidx.lifecycle.*
import com.fadhil.challenge.constant.Gender
import com.fadhil.challenge.data.Resource
import com.fadhil.challenge.data.entities.Student
import com.fadhil.challenge.data.repository.StudentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentViewModel @Inject
constructor(private val studentRepository: StudentRepository) : ViewModel() {

    private lateinit var students: LiveData<Resource<List<Student>>>

    private fun insertStudent(student: Student) {
        viewModelScope.launch {
            studentRepository.insert(student)
        }
    }

    fun getStudents(): LiveData<Resource<List<Student>>> {
        students = loadStudents()
        return students
    }

    private fun loadStudents(): LiveData<Resource<List<Student>>> {
        // If any transformation is needed, this can be simply done by Transformations class ...
        return studentRepository.getAll().asLiveData().map { student -> Resource.success(student) }
    }

    fun getStudentById(id: Int): LiveData<Student> =
        studentRepository.getStudentById(id).asLiveData()

    fun updateStudent(id: Int, name: String, gender: Gender, gpa: Float) {
        val updatedStudent = Student(id, name, gender, gpa)
        viewModelScope.launch {
            studentRepository.update(updatedStudent)
        }
    }

    suspend fun deleteOne(student: Student) = studentRepository.delete(student)

    suspend fun deleteAll() {
        viewModelScope.launch {
            studentRepository.deleteAll()
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