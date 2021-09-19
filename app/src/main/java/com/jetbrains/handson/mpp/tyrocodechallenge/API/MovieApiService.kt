package com.jetbrains.handson.mpp.tyrocodechallenge.netWork

import com.jetbrains.handson.mpp.tyrocodechallenge.data.model.MovieDetail
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("?")
    fun getMovies(@Query("apikey") apikey: String , @Query("s") searchTitle: String):
            Deferred<MovieList>

    @GET("?")
    fun getMovieDetail(@Query("apikey") apikey: String,@Query("t") searchTitle: String):
            Deferred<MovieDetail>

}

