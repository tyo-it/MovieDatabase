package com.ittyo.moviedatabase

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ittyo.moviedatabase.model.Movie
import com.ittyo.moviedatabase.repository.MovieDatabaseRepository
import com.ittyo.moviedatabase.repository.MovieRepository
import com.ittyo.moviedatabase.repository.remote.MovieDatabaseServiceFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchMovieViewModel(
    private val repository: MovieRepository = MovieDatabaseRepository(
        MovieDatabaseServiceFactory.build("6753d9119b9627493ae129f3c3c99151", true)
    )
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