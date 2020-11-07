package com.ittyo.moviedatabase.view.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ittyo.moviedatabase.R
import java.io.IOException

class LoadWithRetryAdapter(private val retry: () -> Unit) : LoadStateAdapter<LoadWithRetryAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.progressBar.isVisible = loadState is LoadState.Loading
        holder.retryButton.isVisible = loadState !is LoadState.Loading
        holder.errorText.isVisible = loadState is LoadState.Error

        holder.retryButton.setOnClickListener { retry() }
        if (loadState is LoadState.Error) {
            if (loadState.error is IOException) {
                holder.errorText.text = holder.itemView.context.getString(R.string.fetch_failed)
            } else {
                holder.errorText.text = loadState.error.localizedMessage
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_item_load_with_retry, parent, false)
        return ViewHolder(itemView)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val retryButton: Button = itemView.findViewById(R.id.retry_button)
        val errorText: TextView = itemView.findViewById(R.id.error_msg)
        val progressBar: ProgressBar = itemView.findViewById(R.id.progress_bar)
    }
}