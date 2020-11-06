 package com.ittyo.moviedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels

 class MainActivity : AppCompatActivity() {

    private val searchMovieViewModel by viewModels<SearchMovieViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchMovieViewModel.lastSearchResult.observe(this) { pagingData ->
            Log.d("PAGING", pagingData.toString())
        }

        searchMovieViewModel.searchMovie("superman")
    }
}