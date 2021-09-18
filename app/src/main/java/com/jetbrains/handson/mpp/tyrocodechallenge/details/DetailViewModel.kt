package com.jetbrains.handson.mpp.tyrocodechallenge.details

import android.app.Application
import androidx.lifecycle.*
import com.jetbrains.handson.mpp.tyrocodechallenge.API.MovieApi
import com.jetbrains.handson.mpp.tyrocodechallenge.netWork.Movie

import com.jetbrains.handson.mpp.tyrocodechallenge.data.model.MovieDetail
import com.jetbrains.handson.mpp.tyrocodechallenge.data.model.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor (
    val repository: Repository,
    var state: SavedStateHandle
    ):ViewModel() {
    private val _selectedMovie = MutableLiveData<MovieDetail>()
    val selectedMovie: LiveData<MovieDetail>
        get() = _selectedMovie

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    init {
        val movie= state.get<Movie>("selectedMovie")
        movie?.let { getMovieDetail(it.title) }
    }

    fun getMovieDetail(title:String) {
        coroutineScope.launch {
            try {
                _selectedMovie.value = repository.getMovieDetail(title).await()
            } catch (e: Exception) {

            }
        }
    }
}

/*class DetailViewModelFactory(
    private val movie: Movie,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(movie, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}*/