package com.ittyo.moviedatabase.repository.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDatabaseService {
    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") query: String,
        @Query("page") page: Int
    ): PageResponse

    @GET("movie/{movie_id}")
    suspend fun movieDetails(
        @Path("movie_id") id: Int,
    ): MovieDetailResponse
}