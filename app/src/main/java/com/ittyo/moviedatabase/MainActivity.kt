 package com.ittyo.moviedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

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

        searchMovieViewModel.lastSearchResult.observe(this) { pagingData ->
            searchJob?.cancel()
            searchJob = lifecycleScope.launch {
                movieListAdapter.submitData(pagingData)
            }
        }

        searchMovieViewModel.searchMovie("superman")
    }
}