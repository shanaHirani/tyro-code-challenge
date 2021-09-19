package com.jetbrains.handson.mpp.tyrocodechallenge.data.model.repository

import com.google.common.truth.Truth
import com.jetbrains.handson.mpp.tyrocodechallenge.data.model.remote.RemoteDataSource
import com.jetbrains.handson.mpp.tyrocodechallenge.netWork.MovieList
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class RepositoryTest {
    lateinit var mockDataSource:RemoteDataSource
    lateinit var movieList:MovieList
    lateinit var repository:Repository
    @Before
    fun setUp() {

        mockDataSource = mockk()
        movieList = mockk()
        repository = Repository(mockDataSource)
        coEvery { mockDataSource.getMovies("heo;i wars") } throws Exception()
        coEvery { mockDataSource.getMovies("star wars") } returns  movieList
    }

    @After
    fun tearDown() {
    }

    @Test(expected = Exception::class)
    fun getMovies_when_error() {

        val repository = Repository(mockDataSource)
        runBlocking {
            repository.getMovies("heo;i wars")

        }
    }

    @Test
    fun getMovies_when_success() {
        runBlocking {
            Truth.assertThat(repository.getMovies("star wars")).isEqualTo(movieList)
        }
    }


    @Test
    fun getMovieDetail() {
    }

    @Test
    fun getRemoteDataSource() {
    }
}