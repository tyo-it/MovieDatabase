package com.ittyo.moviedatabase

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ittyo.moviedatabase.model.Movie
import com.ittyo.moviedatabase.repository.MovieRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchMovieViewModel @ViewModelInject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    var lastSearchResult = MutableLiveData<PagingData<Movie>>()

    fun searchMovie(query: String) {
        viewModelScope.launch {
            repository.searchMovie(query).cachedIn(viewModelScope).collectLatest {
                lastSearchResult.value = it
            }
        }
    }
}