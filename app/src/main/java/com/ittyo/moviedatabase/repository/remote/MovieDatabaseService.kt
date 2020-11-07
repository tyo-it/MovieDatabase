package com.ittyo.moviedatabase.repository.remote

import com.ittyo.moviedatabase.repository.remote.response.MovieDetailsResponse
import com.ittyo.moviedatabase.repository.remote.response.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDatabaseService {
    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") query: String,
        @Query("page") page: Int
    ): SearchResponse

    @GET("movie/{movie_id}")
    suspend fun movieDetails(
        @Path("movie_id") id: Int,
    ): MovieDetailsResponse
}