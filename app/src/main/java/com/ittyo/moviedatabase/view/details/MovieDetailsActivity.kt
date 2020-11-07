package com.ittyo.moviedatabase.view.details

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsActivity: AppCompatActivity() {

    private val viewModel by viewModels<MovieDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        viewModel.data.observe(this) { movieDetails ->
            Log.d("MOVIE", movieDetails.toString())
        }

        val movieId = intent.getIntExtra("MOVIE_ID", 0)
        viewModel.showMovieDetails(movieId)
    }
}