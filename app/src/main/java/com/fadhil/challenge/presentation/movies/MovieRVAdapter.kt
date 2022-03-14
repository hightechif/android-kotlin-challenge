package com.fadhil.challenge.presentation.movies

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fadhil.challenge.domain.model.Movie

abstract class MovieRVAdapter<VH : RecyclerView.ViewHolder>(private val movieEntityList: MutableList<Movie>) : RecyclerView.Adapter<VH>() {

    lateinit var onItemClickedCallback: MovieAdapterDelegate

    fun setOnClickedCallback(callback: MovieAdapterDelegate) {
        this.onItemClickedCallback = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return super.createViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        super.bindViewHolder(holder, position)
    }

    override fun getItemCount(): Int = movieEntityList.size
}