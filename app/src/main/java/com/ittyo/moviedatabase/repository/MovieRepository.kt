package com.ittyo.moviedatabase.repository

import androidx.paging.PagingData
import com.ittyo.moviedatabase.model.Movie
import com.ittyo.moviedatabase.model.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun searchMovie(query: String): Flow<PagingData<Movie>>
    suspend fun getMovieDetails(movieId: Int): MovieDetails
}