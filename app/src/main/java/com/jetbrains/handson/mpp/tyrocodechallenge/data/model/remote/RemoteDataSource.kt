package com.jetbrains.handson.mpp.tyrocodechallenge.data.model.remote

import com.jetbrains.handson.mpp.tyrocodechallenge.API.API
import com.jetbrains.handson.mpp.tyrocodechallenge.netWork.MovieApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(val movieApiService: MovieApiService,val api: API){

    suspend fun getMovies(title:String) = movieApiService.getMovies(api.apiKey,title)
    suspend fun getMovieDetail(title:String) = movieApiService.getMovieDetail(api.apiKey,title)
}