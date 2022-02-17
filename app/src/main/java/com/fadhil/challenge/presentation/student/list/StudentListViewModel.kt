package com.fadhil.challenge.presentation.student.list

import androidx.lifecycle.*
import com.fadhil.challenge.data.source.StudentRepository
import com.fadhil.challenge.domain.model.Student
import com.fadhil.challenge.domain.usecase.StudentInteractor
import com.fadhil.challenge.domain.usecase.StudentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentListViewModel @Inject
constructor(private val studentInteractor: StudentInteractor) : ViewModel() {

    private lateinit var studentsLiveData: LiveData<List<Student>?>

    fun getStudents(): LiveData<List<Student>?> {
        studentsLiveData = loadStudents()
        return studentsLiveData
    }

    private fun loadStudents(): LiveData<List<Student>?> {
        return studentInteractor.getStudentsFlow().asLiveData()
    }

    fun deleteOne(id: Long) {
        viewModelScope.launch {
            studentInteractor.delete(id)
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            studentInteractor.deleteAll()
        }
    }

}