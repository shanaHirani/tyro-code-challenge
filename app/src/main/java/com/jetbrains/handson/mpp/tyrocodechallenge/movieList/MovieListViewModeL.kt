package com.jetbrains.handson.mpp.tyrocodechallenge.movieList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jetbrains.handson.mpp.tyrocodechallenge.API.MovieApi
import com.jetbrains.handson.mpp.tyrocodechallenge.data.model.repository.Repository
import com.jetbrains.handson.mpp.tyrocodechallenge.netWork.Movie
import com.jetbrains.handson.mpp.tyrocodechallenge.netWork.MovieList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import javax.inject.Inject

enum class ApiStatus { LOADING, ERROR, DONE }

@HiltViewModel
class MovieListViewModel @Inject constructor (val repository: Repository): ViewModel() {

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies

    private val _navigateToSelectedMovie = MutableLiveData<Movie>()
    val navigateToSelectedMovie: LiveData<Movie>
        get() = _navigateToSelectedMovie

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )


    fun searchMovies(title:String) {
            coroutineScope.launch {
                try {
                    var listResult = repository.getMovies(title).await()
                    _movies.value = listResult.movieList
                } catch (e: Exception) {
                    _movies.value = ArrayList()
                }
            }
    }


    fun displayMovieDetails(movie: Movie) {
        _navigateToSelectedMovie.value = movie
    }

    fun displayMovieDetailsComplete() {
        _navigateToSelectedMovie.value = null
    }

    fun updateFilter() {

    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}