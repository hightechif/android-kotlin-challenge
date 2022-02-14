package com.fadhil.challenge.presentation.student.list

import androidx.lifecycle.*
import com.fadhil.challenge.data.source.local.entity.StudentEntity
import com.fadhil.challenge.data.source.StudentRepository
import com.fadhil.challenge.domain.model.Student
import com.fadhil.challenge.presentation.student.StudentEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentListViewModel @Inject
constructor(private val studentRepository: StudentRepository) : ViewModel() {

    val studentsLiveData: LiveData<List<Student>> = studentRepository.getStudentsFlow().asLiveData()

    fun deleteOne(student: Student) {
        viewModelScope.launch {
            studentRepository.delete(student)
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            studentRepository.deleteAll()
        }
    }

}