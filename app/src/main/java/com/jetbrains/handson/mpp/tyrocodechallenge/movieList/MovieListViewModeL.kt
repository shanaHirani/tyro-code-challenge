package com.jetbrains.handson.mpp.tyrocodechallenge.movieList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jetbrains.handson.mpp.tyrocodechallenge.netWork.Movie

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

    init {
        getMovies()
    }

    private fun getMovies() {

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