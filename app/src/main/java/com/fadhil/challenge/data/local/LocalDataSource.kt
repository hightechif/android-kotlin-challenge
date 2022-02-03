package com.fadhil.challenge.data.local

import com.fadhil.challenge.data.entities.Movie
import com.fadhil.challenge.data.entities.Student
import com.fadhil.challenge.data.local.room.MoviesDao
import com.fadhil.challenge.data.local.room.StudentDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject
constructor(
    private val moviesDao: MoviesDao,
    private val studentDao: StudentDao
) {

    fun getAllMovies() = moviesDao.getAllMovies()

    suspend fun insertAllMovies(movies: List<Movie>) = moviesDao.insertAll(movies)

    suspend fun insertStudent(student: Student) = studentDao.insert(student)

    fun getAllStudents(): Flow<List<Student>> = studentDao.getAll()

    fun getStudentById(id: Int): Flow<Student> = studentDao.getStudentById(id)

    fun getSmartStudents(): Flow<List<Student>> = studentDao.getSmartStudents()

    suspend fun updateStudent(student: Student) = studentDao.update(student)

    suspend fun deleteStudent(student: Student) = studentDao.delete(student)

    suspend fun deleteAllStudents() = studentDao.deleteAll()

}