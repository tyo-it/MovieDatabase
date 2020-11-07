 package com.ittyo.moviedatabase.view.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.ittyo.moviedatabase.R
import com.ittyo.moviedatabase.view.details.MovieDetailsActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_search_movie.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

 @AndroidEntryPoint
 class SearchMovieActivity : AppCompatActivity() {

    private lateinit var movieListAdapter: MovieListAdapter

    private val searchMovieViewModel by viewModels<SearchMovieViewModel>()
    private var searchJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_movie)

        movieListAdapter = MovieListAdapter{ movieId ->  goToMovieDetailsActivity(movieId) }
        movie_list.layoutManager = LinearLayoutManager(this)
        movie_list.adapter = movieListAdapter.run {
            addLoadStateListener { loadState ->
                movie_list.isVisible = loadState.source.refresh is LoadState.NotLoading
                progress_bar.isVisible = loadState.source.refresh is LoadState.Loading
                retry_button.isVisible = loadState.source.refresh is LoadState.Error

                val errorState = loadState.append as? LoadState.Error
                    ?: loadState.prepend as? LoadState.Error
                    ?: loadState.refresh as? LoadState.Error

                errorState?.let { showErrorToast(it.error) }
            }

            withLoadStateHeaderAndFooter(
                header = LoadWithRetryAdapter{ movieListAdapter.retry() },
                footer = LoadWithRetryAdapter{ movieListAdapter.retry() }
            )
        }

        input_text.setOnEditorActionListener { editText, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                search(editText.text.toString())
                true
            } else {
                false
            }
        }

        retry_button.setOnClickListener { movieListAdapter.retry() }

        searchMovieViewModel.lastSearchResult.observe(this) { pagingData ->
            searchJob?.cancel()
            searchJob = lifecycleScope.launch {
                movieListAdapter.submitData(pagingData)
            }
        }

        searchMovieViewModel.error.observe(this) { error ->
            showErrorToast(error)
        }
    }

     private fun search(query: String) {
         if (query.isBlank()) {
            showInfoToast("empty search input")
            return
         }
         searchMovieViewModel.searchMovie(query)
     }

     private fun showInfoToast(message: String) {
         Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
     }

     private fun showErrorToast(error: Throwable) {
         val message = if (error is IOException || error is HttpException) {
             this.getString(R.string.fetch_failed)
         } else {
             error.localizedMessage
         }
         Toast.makeText(this, message, Toast.LENGTH_LONG).show()
     }

     private fun goToMovieDetailsActivity(movieId: Int) {
         val intent = Intent(this, MovieDetailsActivity::class.java).apply {
             putExtra("MOVIE_ID", movieId)
         }
         startActivity(intent)
     }
}