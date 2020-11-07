package com.ittyo.moviedatabase.view.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.ittyo.moviedatabase.R
import com.ittyo.moviedatabase.model.Movie

class MovieListAdapter(
    private val onMovieItemSelected: (movieId: Int) -> Unit
): PagingDataAdapter<Movie, MovieListAdapter.ViewHolder>(MOVIE_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_item_movie, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)

        holder.movieName.text = movie?.originalTitle
        holder.movieReleaseDate.text = movie?.releaseDate
        holder.movieOverview.text = movie?.overview

        holder.itemView.setOnClickListener {
            movie?.let {
                onMovieItemSelected(it.id)
            }
        }

        Glide.with(holder.itemView.context)
            .load("http://image.tmdb.org/t/p/w185/${movie?.posterPath}")
            .transform(CenterCrop())
            .into(holder.moviePoster)
    }

    companion object {
        private val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val moviePoster: ImageView = view.findViewById(R.id.movie_poster)
        val movieName: TextView = view.findViewById(R.id.movie_name)
        val movieReleaseDate: TextView = view.findViewById(R.id.movie_release_date)
        val movieOverview: TextView = view.findViewById(R.id.movie_overview)
    }
}