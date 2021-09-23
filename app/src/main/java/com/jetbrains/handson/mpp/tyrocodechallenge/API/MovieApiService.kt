package com.jetbrains.handson.mpp.tyrocodechallenge.netWork

import com.jetbrains.handson.mpp.tyrocodechallenge.data.model.MovieDetail
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

interface MovieApiService {

    @GET("?")
    suspend  fun getMovies(@Query("apikey") apikey: String , @Query("s") searchTitle: String): MovieList

    @GET("?")
    suspend fun getMovieDetail(@Query("apikey") apikey: String,@Query("t") searchTitle: String):MovieDetail

}

