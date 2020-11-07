package com.ittyo.moviedatabase.view.details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.google.android.material.chip.Chip
import com.ittyo.moviedatabase.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_movie_details.*
import retrofit2.HttpException
import java.io.IOException

@AndroidEntryPoint
class MovieDetailsActivity: AppCompatActivity() {

    private val viewModel by viewModels<MovieDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        viewModel.data.observe(this) { movieDetails ->
            movieDetails.posterPath?.let {
                Glide.with(this)
                    .load("http://image.tmdb.org/t/p/w154/${it}")
                    .transform(CenterCrop())
                    .into(movie_poster)
            }

            movieDetails.backdropPath?.let {
                Glide.with(this)
                    .load("http://image.tmdb.org/t/p/w300/${it}")
                    .transform(CenterCrop())
                    .into(movie_backdrop)
            }

            movieDetails.tagline?.let {
                if (it.isNotBlank()) {
                    movie_tagline.visibility = View.VISIBLE
                    movie_tagline.text = it
                }
            }

            movie_title.text = movieDetails.title
            movie_release_date.text = movieDetails.releaseDate ?: ""
            movie_overview.text = movieDetails.overview ?: ""
            movie_rating.rating = movieDetails.voteAverage.toFloat()/2

            for(genre in movieDetails.genres) {
                val chip = Chip(this)
                chip.text = genre.name
                chip.isClickable = false
                chip.isCheckable = false
                movie_genres.addView(chip)
            }
        }

        viewModel.error.observe(this) { error ->
            showErrorToast(error)
        }

        val movieId = intent.getIntExtra("MOVIE_ID", 0)
        viewModel.showMovieDetails(movieId)
    }

    private fun showErrorToast(error: Exception) {
        val message = if (error is IOException || error is HttpException) {
            this.getString(R.string.fetch_failed)
        } else {
            error.localizedMessage
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}