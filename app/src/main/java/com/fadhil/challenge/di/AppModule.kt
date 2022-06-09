package com.fadhil.challenge.di

import com.fadhil.challenge.data.source.remote.network.AuthService
import com.fadhil.challenge.data.source.remote.network.MoviesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideMoviesService(retrofit: Retrofit): MoviesService =
        retrofit.create(MoviesService::class.java)

    @Provides
    fun provideAuthService(authRetrofit: Retrofit): AuthService =
        authRetrofit.create(AuthService::class.java)

}