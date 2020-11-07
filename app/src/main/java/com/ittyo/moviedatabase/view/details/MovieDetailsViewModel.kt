package com.ittyo.moviedatabase.view.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ittyo.moviedatabase.model.MovieDetails
import com.ittyo.moviedatabase.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieDetailsViewModel @ViewModelInject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {

    val _data = MutableLiveData<MovieDetails>()
    val _error = MutableLiveData<Exception>()

    val data: LiveData<MovieDetails> = _data
    val error: LiveData<Exception> = _error

    fun showMovieDetails(movieId: Int) {
        viewModelScope.launch {
            try {
                val movieDetails: MovieDetails = movieRepository.getMovieDetails(movieId)
                _data.value = movieDetails
            } catch (e: Exception) {
                _error.value = e
            }
        }
    }
}