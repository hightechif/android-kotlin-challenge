package com.fadhil.challenge.di

import android.content.Context
import com.fadhil.challenge.data.local.LocalDataSource
import com.fadhil.challenge.data.local.room.AppDatabase
import com.fadhil.challenge.data.local.room.MoviesDao
import com.fadhil.challenge.data.remote.RemoteDataSource
import com.fadhil.challenge.data.remote.service.MoviesService
import com.fadhil.challenge.data.repository.MovieRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideRemoteDataSource(moviesService: MoviesService) = RemoteDataSource(moviesService)

    @Singleton
    @Provides
    fun provideLocalDataSource(moviesDao: MoviesDao) = LocalDataSource(moviesDao)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideMoviesDao(db: AppDatabase) = db.moviesDao()

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ) = MovieRepository(remoteDataSource, localDataSource)
}