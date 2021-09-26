package com.jetbrains.handson.mpp.tyrocodechallenge.data.model.remote

import android.util.Log
import com.google.common.truth.Truth
import com.jetbrains.handson.mpp.tyrocodechallenge.API.API
import com.jetbrains.handson.mpp.tyrocodechallenge.data.model.MovieDetail
import com.jetbrains.handson.mpp.tyrocodechallenge.data.model.repository.Repository
import com.jetbrains.handson.mpp.tyrocodechallenge.netWork.MovieApiService
import com.jetbrains.handson.mpp.tyrocodechallenge.netWork.MovieList
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class RemoteDataSourceTest {

    lateinit var mockMovieApiService: MovieApiService
    lateinit var mockAPI: API
    lateinit var mockMovieDetail:MovieDetail
    lateinit var mockRemoteDataSource: RemoteDataSource

    @Before
    fun setUp() {
        mockMovieApiService = mockk()
        mockAPI = mockk()
        mockMovieDetail = mockk()
        mockRemoteDataSource = RemoteDataSource(mockMovieApiService,mockAPI)
        coEvery { mockMovieApiService.getMovieDetail("320f6ab2","heo;i wars") } throws Exception()
        coEvery { mockMovieApiService.getMovieDetail("320f6ab2","star wars") } returns  mockMovieDetail
        coEvery { mockAPI.apiKey} returns "320f6ab2"
    }

    @After
    fun tearDown() {
    }

    @Test(expected = Exception::class)
    fun getMovies_when_error() {
        runBlocking {
            mockRemoteDataSource.getMovieDetail("heo;i wars")
        }
    }

    @Test
    fun getMovies_when_success() {
        runBlocking {
            Truth.assertThat(mockRemoteDataSource.getMovieDetail("star wars")).isEqualTo(mockMovieDetail)
        }
    }

    @Test
    fun chech_api_key() {
        runBlocking {
            Truth.assertThat(mockAPI.apiKey).isEqualTo("320f6ab2")
        }
    }

}