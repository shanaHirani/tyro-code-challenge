package com.jetbrains.handson.mpp.tyrocodechallenge.di

import com.jetbrains.handson.mpp.tyrocodechallenge.API.API
import com.jetbrains.handson.mpp.tyrocodechallenge.API.MovieApi
import com.jetbrains.handson.mpp.tyrocodechallenge.netWork.MovieApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class appModule {

    @Provides
    fun provideMovieApi(api: API) : MovieApiService {
        return api.retrofit.create(MovieApiService::class.java)
    }
}