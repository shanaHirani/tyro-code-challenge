package com.jetbrains.handson.mpp.tyrocodechallenge.movieList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetbrains.handson.mpp.tyrocodechallenge.data.model.repository.Repository
import com.jetbrains.handson.mpp.tyrocodechallenge.netWork.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class ApiStatus { START,EMPTY_LISt, LOADING, ERROR, DONE }

@HiltViewModel
class MovieListViewModel @Inject constructor (val repository: Repository): ViewModel() {

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    init {
        _status.value = ApiStatus.START
    }
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies

    private val _navigateToSelectedMovie = MutableLiveData<Movie>()
    val navigateToSelectedMovie: LiveData<Movie>
        get() = _navigateToSelectedMovie

    fun searchMovies(title:String) {
            viewModelScope.launch {
                try {
                    _status.value = ApiStatus.LOADING
                    var listResult = repository.getMovies(title)
                    _movies.value = listResult.movieList
                    _status.value = ApiStatus.DONE
                    if (_movies.value?.size == 0 || _movies.value?.size == null ){
                        _status.value = ApiStatus.EMPTY_LISt
                    }
                } catch (e: Exception) {
                    _movies.value = ArrayList()
                    _status.value = ApiStatus.ERROR
                    Log.i("sss",e.message.toString())
                }
            }
    }//


    fun displayMovieDetails(movie: Movie) {
        _navigateToSelectedMovie.value = movie
    }

    fun displayMovieDetailsComplete() {
        _navigateToSelectedMovie.value = null
    }

}