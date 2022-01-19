package com.fadhil.challenge.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fadhil.challenge.R
import com.fadhil.challenge.model.Hero
import timber.log.Timber

class HeroGridAdapter(private val heroList: ArrayList<Hero>) : RecyclerView.Adapter<HeroGridAdapter.GridViewHolder>() {

    private lateinit var onItemClickedCallback: HeroCallbackInterface

    fun setOnClickedCallback(callback: HeroCallbackInterface) {
        this.onItemClickedCallback = callback
    }

    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_grid_hero, parent, false)
        return GridViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        val hero = heroList[position]
        Timber.i("Data Hero: $position")
        Glide.with(holder.itemView.context)
            .load(hero.photo)
            .apply(RequestOptions().override(350,550))
            .into(holder.imgPhoto)

        holder.itemView.setOnClickListener {
            onItemClickedCallback.onItemClicked(heroList[holder.bindingAdapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return heroList.size
    }
}