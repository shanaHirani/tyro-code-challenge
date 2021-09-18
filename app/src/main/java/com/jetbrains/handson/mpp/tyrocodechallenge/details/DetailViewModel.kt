package com.jetbrains.handson.mpp.tyrocodechallenge.details

import androidx.lifecycle.*
import com.jetbrains.handson.mpp.tyrocodechallenge.netWork.Movie
import com.jetbrains.handson.mpp.tyrocodechallenge.data.model.MovieDetail
import com.jetbrains.handson.mpp.tyrocodechallenge.data.model.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor (
    private val repository: Repository,
    var state: SavedStateHandle
    ):ViewModel() {
    private val _selectedMovie = MutableLiveData<MovieDetail>()
    val selectedMovie: LiveData<MovieDetail>
        get() = _selectedMovie


    init {
        val movie= state.get<Movie>("selectedMovie")
        movie?.let { getMovieDetail(it.title) }
    }

    private fun getMovieDetail(title:String) {
        viewModelScope.launch {
            try {
                _selectedMovie.value = repository.getMovieDetail(title).await()
            } catch (e: Exception) {

            }
        }
    }
}
