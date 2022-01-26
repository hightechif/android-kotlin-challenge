package com.fadhil.challenge.viewmodels

import androidx.lifecycle.ViewModel
import com.fadhil.challenge.data.room.student.Student
import com.fadhil.challenge.data.room.student.StudentDao
import kotlinx.coroutines.flow.Flow

class StudentViewModel(private val studentDao: StudentDao) : ViewModel() {

    fun allStudents(): Flow<List<Student>> = studentDao.getAll()

}