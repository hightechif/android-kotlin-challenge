package com.fadhil.challenge.ui.student.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.fadhil.challenge.domain.model.Student
import com.fadhil.challenge.domain.usecase.StudentInteractor
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
        // If any transformation is needed, this can be simply done by Transformations class ...
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