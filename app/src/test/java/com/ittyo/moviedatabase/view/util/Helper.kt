package com.ittyo.moviedatabase.view.util

import com.ittyo.moviedatabase.model.Genre
import com.ittyo.moviedatabase.model.Movie
import com.ittyo.moviedatabase.model.MovieDetails
import com.ittyo.moviedatabase.repository.remote.response.GenreResponse
import com.ittyo.moviedatabase.repository.remote.response.MovieDetailsResponse

fun makeMovieDetailsResponse(
    id: Int = 0,
    adult: Boolean = false,
    backdropPath: String = "",
    budget: Int = 0,
    genres: List<GenreResponse> = emptyList(),
    homepage: String = "",
    imdbId: String = "",
    originalLanguage: String = "",
    originalTitle: String = "",
    overview: String = "",
    popularity: Double = 0.0,
    posterPath: String = "",
    releaseDate: String = "",
    revenue: Int = 0,
    runtime: Int = 0,
    status: String = "",
    tagline: String = "",
    title: String = "",
    video: Boolean = false,
    voteAverage: Double = 0.0,
    voteCount: Int = 0
): MovieDetailsResponse {
    return MovieDetailsResponse(
        id,
        adult,
        backdropPath,
        budget,
        genres,
        homepage,
        imdbId,
        originalLanguage,
        originalTitle,
        overview,
        popularity,
        posterPath,
        releaseDate,
        revenue,
        runtime,
        status,
        tagline,
        title,
        video,
        voteAverage,
        voteCount
    )
}

fun makeMovieDetails(
    id: Int = 0,
    adult: Boolean = false,
    backdropPath: String = "",
    budget: Int = 0,
    genres: List<Genre> = emptyList(),
    homepage: String = "",
    imdbId: String = "",
    originalLanguage: String = "",
    originalTitle: String = "",
    overview: String = "",
    popularity: Double = 0.0,
    posterPath: String = "",
    releaseDate: String = "",
    revenue: Int = 0,
    runtime: Int = 0,
    status: String = "",
    tagline: String = "",
    title: String = "",
    video: Boolean = false,
    voteAverage: Double = 0.0,
    voteCount: Int = 0
): MovieDetails {
    return MovieDetails(
        id,
        adult,
        backdropPath,
        budget,
        genres,
        homepage,
        imdbId,
        originalLanguage,
        originalTitle,
        overview,
        popularity,
        posterPath,
        releaseDate,
        revenue,
        runtime,
        status,
        tagline,
        title,
        video,
        voteAverage,
        voteCount
    )
}

fun makeMovie(id: Int = 0,
              popularity: Double = 0.0,
              voteCount: Int = 0,
              video: Boolean = false,
              posterPath: String = "",
              backdropPath: String = "",
              originalLanguage: String = "",
              originalTitle: String = "",
              genreIds: List<Int> = emptyList(),
              releaseDate: String = "",
              overview: String = "",
              voteAverage: Double = 0.0,
              title: String = "",
              adult: Boolean = false): Movie {
    return Movie(
        id,
        popularity,
        voteCount,
        video,
        posterPath,
        adult,
        backdropPath,
        originalLanguage,
        originalTitle,
        genreIds,
        title,
        voteAverage,
        overview,
        releaseDate
    )
}