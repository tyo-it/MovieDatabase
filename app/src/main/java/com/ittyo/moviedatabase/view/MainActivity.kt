 package com.ittyo.moviedatabase.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.ittyo.moviedatabase.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

 @AndroidEntryPoint
 class MainActivity : AppCompatActivity() {

    private lateinit var movieListAdapter: MovieListAdapter

    private val searchMovieViewModel by viewModels<SearchMovieViewModel>()
    private var searchJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieListAdapter = MovieListAdapter()
        movie_list.adapter = movieListAdapter
        movie_list.layoutManager = GridLayoutManager(this, 2)

        input_text.setOnEditorActionListener { editText, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                search(editText.text.toString())
                true
            } else {
                false
            }
        }

        searchMovieViewModel.lastSearchResult.observe(this) { pagingData ->
            searchJob?.cancel()
            searchJob = lifecycleScope.launch {
                movieListAdapter.submitData(pagingData)
            }
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
}