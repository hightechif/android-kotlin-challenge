package com.fadhil.challenge.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fadhil.challenge.data.entities.Movie
import com.fadhil.challenge.view.callback.MovieCallback

abstract class MovieRVAdapter<VH : RecyclerView.ViewHolder>(private val movieList: ArrayList<Movie>) : RecyclerView.Adapter<VH>() {

    lateinit var onItemClickedCallback: MovieCallback

    fun setOnClickedCallback(callback: MovieCallback) {
        this.onItemClickedCallback = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return super.createViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        super.bindViewHolder(holder, position)
    }

    override fun getItemCount(): Int = movieList.size
}