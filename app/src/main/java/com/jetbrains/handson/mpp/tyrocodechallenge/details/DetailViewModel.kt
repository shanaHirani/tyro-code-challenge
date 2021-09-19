package com.jetbrains.handson.mpp.tyrocodechallenge.details

import androidx.lifecycle.*
import com.jetbrains.handson.mpp.tyrocodechallenge.netWork.Movie
import com.jetbrains.handson.mpp.tyrocodechallenge.data.model.MovieDetail
import com.jetbrains.handson.mpp.tyrocodechallenge.data.model.repository.Repository
import com.jetbrains.handson.mpp.tyrocodechallenge.movieList.ApiStatus
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

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    init {
        val movie= state.get<Movie>("selectedMovie")
        movie?.let { getMovieDetail(it.title) }
        _status.value = ApiStatus.LOADING
    }

    private fun getMovieDetail(title:String) {
        viewModelScope.launch {
            try {
                _status.value = ApiStatus.LOADING
                _selectedMovie.value = repository.getMovieDetail(title).await()
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
            }
        }
    }
}
