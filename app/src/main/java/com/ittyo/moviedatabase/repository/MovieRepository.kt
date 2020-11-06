package com.ittyo.moviedatabase.repository

import androidx.paging.PagingData
import com.ittyo.moviedatabase.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun searchMovie(query: String): Flow<PagingData<Movie>>
}