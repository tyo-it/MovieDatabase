package com.ittyo.moviedatabase.model

import com.ittyo.moviedatabase.repository.remote.response.MovieResponse

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

fun MovieResponse.toMovieModel(): Movie {
    return Movie(
        id = this.id,
        popularity = this.popularity,
        voteCount = this.voteCount,
        video = this.video,
        posterPath = this.posterPath,
        adult = this.adult,
        backdropPath = this.backdropPath,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        genreIds = this.genreIds,
        title = this.title,
        voteAverage = this.voteAverage,
        overview = this.overview,
        releaseDate = this.releaseDate
    )
}