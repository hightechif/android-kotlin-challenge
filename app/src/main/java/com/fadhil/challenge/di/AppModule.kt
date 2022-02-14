package com.fadhil.challenge.di

import com.fadhil.challenge.data.source.MovieRepository
import com.fadhil.challenge.data.source.StudentRepository
import com.fadhil.challenge.data.source.local.MovieLocalDataSource
import com.fadhil.challenge.data.source.local.StudentLocalDataSource
import com.fadhil.challenge.data.source.local.room.AppDatabase
import com.fadhil.challenge.data.source.local.room.MoviesDao
import com.fadhil.challenge.data.source.local.room.StudentDao
import com.fadhil.challenge.data.source.remote.MovieRemoteDataSource
import com.fadhil.challenge.data.source.remote.network.MoviesService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://api.themoviedb.org"

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideMoviesService(retrofit: Retrofit): MoviesService =
        retrofit.create(MoviesService::class.java)

    @Singleton
    @Provides
    fun provideMovieRemoteDataSource(
        moviesService: MoviesService
    ) = MovieRemoteDataSource(moviesService)

    @Singleton
    @Provides
    fun provideMovieLocalDataSource(
        moviesDao: MoviesDao
    ) = MovieLocalDataSource(moviesDao)

    @Singleton
    @Provides
    fun provideStudentLocalDataSource(
        studentDao: StudentDao
    ) = StudentLocalDataSource(studentDao)

    @Singleton
    @Provides
    fun provideMoviesDao(db: AppDatabase) = db.moviesDao()

    @Singleton
    @Provides
    fun provideStudentDao(db: AppDatabase) = db.studentDao()

    @Singleton
    @Provides
    fun provideMovieRepository(
        movieRemoteDataSource: MovieRemoteDataSource,
        movieLocalDataSource: MovieLocalDataSource
    ) = MovieRepository(movieRemoteDataSource, movieLocalDataSource)

    @Singleton
    @Provides
    fun provideStudentRepository(
        studentLocalDataSource: StudentLocalDataSource
    ) = StudentRepository(studentLocalDataSource)

}