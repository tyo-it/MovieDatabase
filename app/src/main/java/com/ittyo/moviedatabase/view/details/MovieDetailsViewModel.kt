package com.ittyo.moviedatabase.view.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ittyo.moviedatabase.model.MovieDetails
import com.ittyo.moviedatabase.repository.MovieRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {

    val data = MutableLiveData<MovieDetails>()

    fun showMovieDetails(movieId: Int) {
        viewModelScope.launch {
            val movieDetails: MovieDetails = movieRepository.getMovieDetails(movieId)
            data.value = movieDetails
        }
    }
}