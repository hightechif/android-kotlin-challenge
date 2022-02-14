package com.fadhil.challenge.ui.student.list

import androidx.lifecycle.*
import com.fadhil.challenge.data.source.local.entity.Student
import com.fadhil.challenge.data.source.StudentRepository
import com.fadhil.challenge.ui.student.StudentEvent
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