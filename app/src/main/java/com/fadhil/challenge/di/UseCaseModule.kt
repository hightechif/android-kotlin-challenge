package com.fadhil.challenge.di

import com.fadhil.challenge.data.source.MovieRepository
import com.fadhil.challenge.data.source.StudentRepository
import com.fadhil.challenge.domain.usecase.MovieInteractor
import com.fadhil.challenge.domain.usecase.StudentInteractor
import com.fadhil.challenge.domain.usecase.StudentUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideStudentInteractor(
        studentRepository: StudentRepository
    ) = StudentInteractor(studentRepository)

    @Provides
    @Singleton
    fun provideMovieInteractor(
        movieRepository: MovieRepository
    ) = MovieInteractor(movieRepository)
}