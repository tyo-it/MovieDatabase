package com.ittyo.moviedatabase.model

import com.ittyo.moviedatabase.repository.remote.MovieResponse

data class Movie (
    val id : Int,
    val popularity : Double,
    val voteCount : Int,
    val video : Boolean,
    val posterPath : String?,
    val adult : Boolean,
    val backdropPath : String?,
    val originalLanguage : String,
    val originalTitle : String,
    val genreIds : List<Int>,
    val title : String,
    val voteAverage : Double,
    val overview : String,
    val releaseDate : String?
)

fun fromResponse(movieResponse: MovieResponse): Movie {
    return Movie(
        id = movieResponse.id,
        popularity = movieResponse.popularity,
        voteCount = movieResponse.voteCount,
        video = movieResponse.video,
        posterPath = movieResponse.posterPath,
        adult = movieResponse.adult,
        backdropPath = movieResponse.backdropPath,
        originalLanguage = movieResponse.originalLanguage,
        originalTitle = movieResponse.originalTitle,
        genreIds = movieResponse.genreIds,
        title = movieResponse.title,
        voteAverage = movieResponse.voteAverage,
        overview = movieResponse.overview,
        releaseDate = movieResponse.releaseDate
    )
}