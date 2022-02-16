package com.fadhil.challenge.di

import android.content.SharedPreferences
import com.fadhil.challenge.data.source.MovieRepository
import com.fadhil.challenge.data.source.StudentRepository
import com.fadhil.challenge.data.source.local.MovieLocalDataSource
import com.fadhil.challenge.data.source.local.SessionLocalDataSource
import com.fadhil.challenge.data.source.local.StudentLocalDataSource
import com.fadhil.challenge.data.source.local.room.StudentDao
import com.fadhil.challenge.data.source.remote.MovieRemoteDataSource
import com.fadhil.challenge.data.source.remote.SessionRemoteDataSource
import com.fadhil.challenge.data.source.remote.network.AuthService
import com.fadhil.challenge.data.source.remote.network.MoviesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRemoteDataSource(
        moviesService: MoviesService
    ) = MovieRemoteDataSource(moviesService)

    @Singleton
    @Provides
    fun provideMovieLocalDataSource(
        sharedPreferences: SharedPreferences
    ) = MovieLocalDataSource(sharedPreferences)

    @Singleton
    @Provides
    fun provideSessionRemoteDataSource(
        authService: AuthService
    ) = SessionRemoteDataSource(authService)

    @Singleton
    @Provides
    fun provideSessionLocalDataSource(
        sharedPreferences: SharedPreferences
    ) = SessionLocalDataSource(sharedPreferences)

    @Singleton
    @Provides
    fun provideStudentLocalDataSource(
        studentDao: StudentDao
    ) = StudentLocalDataSource(studentDao)

    @Singleton
    @Provides
    fun provideMovieRepository(
        sessionRemoteDataSource: SessionRemoteDataSource,
        sessionLocalDataSource: SessionLocalDataSource,
        movieRemoteDataSource: MovieRemoteDataSource,
        movieLocalDataSource: MovieLocalDataSource
    ) = MovieRepository(sessionRemoteDataSource, sessionLocalDataSource,movieRemoteDataSource, movieLocalDataSource)

    @Singleton
    @Provides
    fun provideStudentRepository(
        studentLocalDataSource: StudentLocalDataSource
    ) = StudentRepository(studentLocalDataSource)

}