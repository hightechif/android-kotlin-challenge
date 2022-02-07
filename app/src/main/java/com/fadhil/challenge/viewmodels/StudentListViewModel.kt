package com.fadhil.challenge.viewmodels

import androidx.lifecycle.*
import com.fadhil.challenge.data.entities.Student
import com.fadhil.challenge.data.repository.StudentRepository
import com.fadhil.challenge.view.event.StudentEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentListViewModel @Inject
constructor(private val studentRepository: StudentRepository) : ViewModel() {

    val studentsLiveData: LiveData<List<Student>> = studentRepository.studentsFlow.asLiveData()
    private val studentEventState: MutableLiveData<StudentEvent> = MutableLiveData<StudentEvent>()

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