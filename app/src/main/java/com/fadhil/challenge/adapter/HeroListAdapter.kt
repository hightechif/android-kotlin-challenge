package com.fadhil.challenge.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fadhil.challenge.R
import com.fadhil.challenge.model.Hero

class HeroListAdapter(private val heroList: ArrayList<Hero>) : RecyclerView.Adapter<HeroListAdapter.ListViewHolder>() {

    private lateinit var onItemClickedCallback: HeroCallbackInterface

    fun setOnClickedCallback(callback: HeroCallbackInterface) {
        this.onItemClickedCallback = callback
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvDetail: TextView = itemView.findViewById(R.id.tv_item_detail)
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_hero, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val hero = heroList[position]
        Glide.with(holder.itemView.context)
            .load(hero.photo)
            .apply(RequestOptions().override(55,55))
            .into(holder.imgPhoto)
        holder.tvName.text = hero.name
        holder.tvDetail.text = hero.detail

        holder.itemView.setOnClickListener {
            onItemClickedCallback.onItemClicked(heroList[holder.bindingAdapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return heroList.size
    }
}