package com.jetbrains.handson.mpp.tyrocodechallenge.movieList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jetbrains.handson.mpp.tyrocodechallenge.netWork.Movie
import com.jetbrains.handson.mpp.tyrocodechallenge.netWork.MovieApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

enum class MarsApiStatus { LOADING, ERROR, DONE }

class MovieListViewModel: ViewModel() {
    private val _status = MutableLiveData<MarsApiStatus>()
    val status: LiveData<MarsApiStatus>
        get() = _status

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies

    private val _navigateToSelectedMovie = MutableLiveData<Movie>()
    val navigateToSelectedProperty: LiveData<Movie>
        get() = _navigateToSelectedMovie

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    init {
        getMovies()
    }



    private fun getMovies() {
        MovieApi.retrofitService.getMovies().enqueue( object: Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                _response.value = "Failure: " + t.message
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                _response.value = response.body()
            }
        })
    }

    fun displayMovieDetails(movie: Movie) {
        _navigateToSelectedMovie.value = movie
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedMovie.value = null
    }

    fun updateFilter() {

    }
}