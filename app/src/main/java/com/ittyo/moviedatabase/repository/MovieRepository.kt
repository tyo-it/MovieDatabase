package com.ittyo.moviedatabase.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.ittyo.moviedatabase.model.Movie
import com.ittyo.moviedatabase.model.fromResponse
import com.ittyo.moviedatabase.repository.remote.MovieDatabaseService
import com.ittyo.moviedatabase.repository.remote.SEARCH_PAGE_SIZE
import com.ittyo.moviedatabase.repository.remote.SEARCH_STARTING_PAGE_INDEX
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException

class MovieRepository(private val movieDatabaseService: MovieDatabaseService) {

    fun searchMovie(query: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = SEARCH_PAGE_SIZE,
                prefetchDistance = SEARCH_PAGE_SIZE,
                maxSize = SEARCH_PAGE_SIZE * 4,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { SearchMoviePagingSource(movieDatabaseService, query) }
        ).flow
    }
}

class SearchMoviePagingSource(
    private val movieDatabaseService: MovieDatabaseService,
    private val query: String
): PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val position = params.key ?: SEARCH_STARTING_PAGE_INDEX
        return try {
            val response = movieDatabaseService.searchMovie(query, position)
            val movies = response.results
            LoadResult.Page(
                data = movies.map { fromResponse(it) },
                prevKey = if (position == SEARCH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (movies.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}