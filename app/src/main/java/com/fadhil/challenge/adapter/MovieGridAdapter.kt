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
import com.fadhil.challenge.model.Movie
import timber.log.Timber

class MovieGridAdapter(private val movieList: ArrayList<Movie>) : MovieRVAdapter<MovieGridAdapter.GridViewHolder>(movieList) {

    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPoster: ImageView = itemView.findViewById(R.id.img_item_poster)
        val tvRating: TextView = itemView.findViewById(R.id.tv_item_rating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_grid_movie, parent, false)
        return GridViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        val movie = movieList[position]
        Timber.i("Data Movie: $position")
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
            onItemClickedCallback.onItemClicked(movieList[holder.bindingAdapterPosition])
        }
    }
}