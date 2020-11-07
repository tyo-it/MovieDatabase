package com.ittyo.moviedatabase.view.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ittyo.moviedatabase.model.Genre
import com.ittyo.moviedatabase.model.MovieDetails
import com.ittyo.moviedatabase.model.toMovieDetailsModel
import com.ittyo.moviedatabase.repository.MovieDatabaseRepository
import com.ittyo.moviedatabase.repository.MovieRepository
import com.ittyo.moviedatabase.repository.remote.MovieDatabaseService
import com.ittyo.moviedatabase.repository.remote.MovieDatabaseServiceFactory
import com.ittyo.moviedatabase.repository.remote.response.GenreResponse
import com.ittyo.moviedatabase.repository.remote.response.MovieDetailsResponse
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieDetailsViewModelTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var movieDatabaseService: MovieDatabaseService

    @Mock
    lateinit var observer: Observer<MovieDetails>

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private lateinit var viewModel: MovieDetailsViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testCoroutineDispatcher)
        val movieRepository = MovieDatabaseRepository(movieDatabaseService)
        viewModel = MovieDetailsViewModel(movieRepository)
    }

    @After
    fun exit() {
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `showMovieDetails will emit movie details from service`() = runBlocking {
        val movieDetailsResponse = makeMovieDetailsResponse(id= 21, title = "Doraemon")
        val movieDetails = makeMovieDetails(id=21, title = "Doraemon")

        whenever(movieDatabaseService.movieDetails(any())).thenAnswer {
            movieDetailsResponse
        }

        viewModel.data.observeForever(observer)
        viewModel.showMovieDetails(21)

        verify(observer).onChanged(movieDetails)
    }

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
    ): MovieDetails{
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
}