package com.jetbrains.handson.mpp.tyrocodechallenge.netWork

import com.jetbrains.handson.mpp.tyrocodechallenge.data.model.MovieDetail
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("?apikey=320f6ab2")
    fun getMovies(@Query("s") searchTitle: String):
            Deferred<MovieList>

    @GET("?apikey=320f6ab2")
    fun getMovieDetail(@Query("t") searchTitle: String):
            Deferred<MovieDetail>

}

