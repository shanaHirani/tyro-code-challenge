package com.jetbrains.handson.mpp.tyrocodechallenge.data.model.repository

import android.util.Log
import com.jetbrains.handson.mpp.tyrocodechallenge.data.model.MovieDetail
import com.jetbrains.handson.mpp.tyrocodechallenge.data.model.remote.RemoteDataSource
import com.jetbrains.handson.mpp.tyrocodechallenge.netWork.MovieList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor( val remoteDataSource:RemoteDataSource) {

    suspend fun getMovies(title:String):MovieList {
        var movieList:MovieList
        withContext(Dispatchers.IO){
            //Log.i("sssssRemoteDataSource,Thread.currentThread().name)
            movieList =  remoteDataSource.getMovies(title)
        }
        return movieList
    }
    suspend fun getMovieDetail(title:String) : MovieDetail{
        var movieDetail:MovieDetail
        withContext(Dispatchers.IO){
            //Log.i("sssssRemoteDataSource,Thread.currentThread().name)
            movieDetail =  remoteDataSource.getMovieDetail(title)
        }
        return movieDetail
    }
}