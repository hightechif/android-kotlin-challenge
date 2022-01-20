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

class MovieListAdapter(private val movieList: ArrayList<Movie>) : MovieRVAdapter<MovieListAdapter.ListViewHolder>(movieList) {

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {
            with(itemView) {
                val tvTitle: TextView = findViewById(R.id.tv_item_title)
                val tvRelease: TextView = findViewById(R.id.tv_item_release)
                val imgPoster: ImageView = findViewById(R.id.img_item_poster)
                val tvRating: TextView = findViewById(R.id.tv_item_rating)
                val tvOverview: TextView = findViewById(R.id.tv_item_overview)
                tvTitle.text = movie.title
                tvRelease.text = movie.release
                /**
                 * How to load image with hard coded way
                 *    val imgUrl = URL("https://image.tmdb.org/t/p/w200/${movie.poster}")
                 *    val imgVal = BitmapFactory.decodeStream(imgUrl.openConnection().getInputStream())
                 *    imgPoster.setImageBitmap(imgVal)
                 * */
                Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w500/${movie.poster}")
                    .apply(RequestOptions().override(165, 250))
                    .into(imgPoster)
                tvRating.text = movie.rating.toString()
                tvOverview.text = movie.overview
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_movie, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val movie = movieList[position]
        Timber.i("Data Movie: $position")
        holder.bind(movie)
        holder.itemView.setOnClickListener {
            onItemClickedCallback.onItemClicked(movieList[holder.bindingAdapterPosition])
        }
    }
}