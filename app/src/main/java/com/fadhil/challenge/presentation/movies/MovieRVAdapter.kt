package com.fadhil.challenge.presentation.movies

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fadhil.challenge.data.source.local.entity.MovieEntity

abstract class MovieRVAdapter<VH : RecyclerView.ViewHolder>(private val movieEntityList: ArrayList<MovieEntity>) : RecyclerView.Adapter<VH>() {

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

    override fun getItemCount(): Int = movieEntityList.size
}