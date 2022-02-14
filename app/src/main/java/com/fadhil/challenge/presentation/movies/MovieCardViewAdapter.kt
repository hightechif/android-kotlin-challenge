package com.fadhil.challenge.presentation.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fadhil.challenge.R
import com.fadhil.challenge.data.source.local.entity.MovieEntity

class MovieCardViewAdapter(private val movieEntityList: ArrayList<MovieEntity>) : MovieRVAdapter<MovieCardViewAdapter.CardViewHolder>(movieEntityList) {

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tv_item_title)
        val tvRelease: TextView = itemView.findViewById(R.id.tv_item_release)
        val imgPoster: ImageView = itemView.findViewById(R.id.img_item_poster)
        val tvRating: TextView = itemView.findViewById(R.id.tv_item_rating)
        val btnFavorite: TextView = itemView.findViewById(R.id.btn_set_favorite)
        val btnShare: TextView = itemView.findViewById(R.id.btn_set_share)
        val tvOverview: TextView = itemView.findViewById(R.id.tv_item_overview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_cardview_movie, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val movie = movieEntityList[position]
        holder.tvTitle.text = movie.title
        holder.tvRelease.text = movie.release
        holder.tvOverview.text = movie.overview
        /**
         * How to load image with hard coded way
         *    val imgUrl = URL("https://image.tmdb.org/t/p/w200/${movie.poster}")
         *    val imgVal = BitmapFactory.decodeStream(imgUrl.openConnection().getInputStream())
         *    imgPoster.setImageBitmap(imgVal)
         * */
        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w500/${movie.poster}")
            .apply(RequestOptions().override(165, 250))
            .into(holder.imgPoster)
        holder.tvRating.text = movie.rating.toString()

        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Kamu memilih " + movieEntityList[holder.bindingAdapterPosition].title, Toast.LENGTH_SHORT).show()
        }

        holder.btnFavorite.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Favorite " + movieEntityList[holder.bindingAdapterPosition].title, Toast.LENGTH_SHORT).show()
        }

        holder.btnShare.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Share " + movieEntityList[holder.bindingAdapterPosition].title, Toast.LENGTH_SHORT).show()
        }
    }
}