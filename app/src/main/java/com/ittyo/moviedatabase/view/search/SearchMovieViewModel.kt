package com.ittyo.moviedatabase.view.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
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

    private val _lastSearchResult = MutableLiveData<PagingData<Movie>>()
    private val _error = MutableLiveData<Exception>()

    val lastSearchResult: LiveData<PagingData<Movie>> = _lastSearchResult
    val error: LiveData<Exception> = _error

    fun searchMovie(query: String) {
        viewModelScope.launch {
            try {
                repository.searchMovie(query).cachedIn(viewModelScope).collectLatest {
                    _lastSearchResult.value = it
                }
            } catch (e: Exception) {
                _error.value = e
            }
        }
    }
}