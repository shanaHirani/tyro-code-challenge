package com.jetbrains.handson.mpp.tyrocodechallenge.data.model.repository

import com.jetbrains.handson.mpp.tyrocodechallenge.data.model.remote.RemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor( val remoteDataSource:RemoteDataSource) {

    suspend fun getMovies(title:String) = remoteDataSource.getMovies(title)
    suspend fun getMovieDetail(title:String) = remoteDataSource.getMovieDetail(title)
}