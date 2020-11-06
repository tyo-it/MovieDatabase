package com.ittyo.moviedatabase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.paging.PagingData
import com.ittyo.moviedatabase.model.Movie
import com.ittyo.moviedatabase.repository.MovieRepository
import com.ittyo.moviedatabase.repository.remote.MovieResponse
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
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

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SearchMovieViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: MovieRepository

    @Mock
    lateinit var observer: Observer<PagingData<Movie>>

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private lateinit var viewModel: SearchMovieViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testCoroutineDispatcher)
        viewModel = SearchMovieViewModel(repository)
    }

    @After
    fun exit() {
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when user search movie it will emit the search result`(){
        whenever(repository.searchMovie(any())).thenAnswer {
            flow {
                val movie = makeMovie(title= "Doraemon")
                emit(PagingData.from(listOf(movie)))
            }
        }
        viewModel.lastSearchResult.observeForever(observer)
        viewModel.searchMovie("Dora")

        verify(observer).onChanged(any())
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
}