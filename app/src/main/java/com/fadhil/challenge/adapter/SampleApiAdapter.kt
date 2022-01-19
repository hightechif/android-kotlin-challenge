package com.fadhil.challenge.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fadhil.challenge.R
import com.fadhil.challenge.model.SampleApi

class SampleApiAdapter(private val list: ArrayList<SampleApi>): RecyclerView.Adapter<SampleApiAdapter.PostViewHolder>() {

    inner class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(post: SampleApi) {
            with(itemView) {
                val data = "id: ${post.id}\n" +
                        "title: ${post.title}\n" +
                        "text: ${post.text}"
                val tvText: TextView = findViewById(R.id.tvApiText)
                tvText.text = data
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_sample_api, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}