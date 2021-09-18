package com.jetbrains.handson.mpp.tyrocodechallenge.details

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jetbrains.handson.mpp.tyrocodechallenge.API.MovieApi
import com.jetbrains.handson.mpp.tyrocodechallenge.netWork.Movie

import com.jetbrains.handson.mpp.tyrocodechallenge.data.model.MovieDetail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DetailViewModel(movie: Movie, app: Application):ViewModel() {
    private val _selectedMovie = MutableLiveData<MovieDetail>()
    val selectedMovie: LiveData<MovieDetail>
        get() = _selectedMovie

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    init {
       getMovieDetail(movie.title)
    }

    fun getMovieDetail(title:String) {
        coroutineScope.launch {
            try {
                _selectedMovie.value = MovieApi.retrofitService.getMovieDetail(title).await()
            } catch (e: Exception) {

            }
        }
    }
}

class DetailViewModelFactory(
    private val movie: Movie,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(movie, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}