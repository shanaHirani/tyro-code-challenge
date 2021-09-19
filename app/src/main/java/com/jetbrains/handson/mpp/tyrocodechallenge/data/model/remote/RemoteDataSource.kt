package com.jetbrains.handson.mpp.tyrocodechallenge.data.model.remote

import com.jetbrains.handson.mpp.tyrocodechallenge.netWork.MovieApiService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(val movieApiService: MovieApiService){
    suspend fun getMovies(title:String) = movieApiService.getMovies(title)
    suspend fun getMovieDetail(title:String) = movieApiService.getMovieDetail(title)
}