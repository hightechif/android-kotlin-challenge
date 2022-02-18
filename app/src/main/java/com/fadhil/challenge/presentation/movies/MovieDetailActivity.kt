package com.fadhil.challenge.presentation.movies

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.fadhil.challenge.databinding.ActivityMovieDetailBinding
import com.fadhil.challenge.presentation.heroes.HeroDetailActivity

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var poster: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Movie Detail"

        val bundle: Bundle? = intent.extras
        val title = bundle?.getString("MOVIE_TITLE")
        val rating = bundle?.getFloat("MOVIE_RATING")
        val overview = bundle?.getString("MOVIE_OVERVIEW")
        val posterEndpoint = bundle?.getString("MOVIE_POSTER")

        binding.tvDetailTitle.text = title
        binding.tvItemRating.text = rating.toString()
        binding.tvDetailDescription.text = overview

        Glide.with(this).asBitmap()
            .load("https://image.tmdb.org/t/p/w500/${posterEndpoint}")
            .apply(RequestOptions().override(165, 250))
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    poster = resource
                    binding.imgDetail.setImageBitmap(poster)
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }

            })
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putString(HeroDetailActivity.DETAIL_TITLE, binding.tvDetailTitle.text.toString())
        outState.putString(
            HeroDetailActivity.DETAIL_DESCRIPTION,
            binding.tvDetailDescription.text.toString()
        )
        outState.putString(HeroDetailActivity.DETAIL_PHOTO, binding.imgDetail.resources.toString())
    }
}