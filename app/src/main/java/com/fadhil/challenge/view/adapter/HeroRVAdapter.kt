package com.fadhil.challenge.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fadhil.challenge.model.Hero
import com.fadhil.challenge.view.callback.HeroCallback

abstract class HeroRVAdapter<VH : RecyclerView.ViewHolder>(private val heroList: ArrayList<Hero>) : RecyclerView.Adapter<VH>() {

    lateinit var onItemClickedCallback: HeroCallback

    fun setOnClickedCallback(callback: HeroCallback) {
        this.onItemClickedCallback = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return super.createViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        super.bindViewHolder(holder, position)
    }

    override fun getItemCount(): Int = heroList.size
}