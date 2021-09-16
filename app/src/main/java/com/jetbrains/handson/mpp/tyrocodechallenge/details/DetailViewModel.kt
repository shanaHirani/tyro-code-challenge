package com.jetbrains.handson.mpp.tyrocodechallenge.details

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jetbrains.handson.mpp.tyrocodechallenge.netWork.Movie

class DetailViewModel(movie: Movie, app: Application):ViewModel() {
    private val _selectedMovie = MutableLiveData<Movie>()
    val selectedMovie: LiveData<Movie>
        get() = _selectedMovie

    // Initialize the _selectedProperty MutableLiveData
    init {
        _selectedMovie.value = movie
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