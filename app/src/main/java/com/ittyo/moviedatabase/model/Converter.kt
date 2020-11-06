package com.ittyo.moviedatabase.model

import com.ittyo.moviedatabase.repository.remote.response.GenreResponse
import com.ittyo.moviedatabase.repository.remote.response.MovieDetailResponse
import com.ittyo.moviedatabase.repository.remote.response.MovieResponse

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

fun MovieDetailResponse.toMovieDetailModel(): MovieDetail {
    return MovieDetail(
        id = this.id,
        adult = this.adult,
        backdropPath = this.backdropPath,
        budget = this.budget,
        genres = this.genres.map { it.toGenreModel() },
        homepage = this.homepage,
        imdbId = this.imdbId,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        revenue = this.revenue,
        runtime = this.runtime,
        status = this.status,
        tagline = this.tagline,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}

fun GenreResponse.toGenreModel(): Genre {
    return Genre(
        id = this.id,
        name = this.name
    )
}