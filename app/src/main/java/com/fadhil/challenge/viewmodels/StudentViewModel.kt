package com.fadhil.challenge.viewmodels

import androidx.lifecycle.ViewModel
import com.fadhil.challenge.data.room.student.Student
import com.fadhil.challenge.data.room.student.StudentDao

class StudentViewModel(private val studentDao: StudentDao) : ViewModel() {

    fun allStudents(): List<Student> = studentDao.getAll()

}