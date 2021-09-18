package com.jetbrains.handson.mpp.tyrocodechallenge.data.model.remote

import com.jetbrains.handson.mpp.tyrocodechallenge.API.MovieApi
import retrofit2.http.Query

class RemoteDataSource {
    suspend fun getMovies(title:String) = MovieApi.retrofitService.getMovies(title)
    suspend fun getMovieDetail(title:String) = MovieApi.retrofitService.getMovieDetail(title)
}