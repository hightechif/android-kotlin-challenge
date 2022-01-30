package com.fadhil.challenge.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.fadhil.challenge.databinding.ActivityDetailBinding
import timber.log.Timber

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val DETAIL_TITLE = "detail_title"
        const val DETAIL_DESCRIPTION = "detail_description"
        const val DETAIL_PHOTO = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Detail Page"

        val bundle: Bundle? = intent.extras
        val title = bundle?.getString("HERO_NAME")
        val description = bundle?.getString("HERO_DETAIL")
        Timber.i("$title $description")
        val photoId = bundle?.getInt("HERO_PHOTO")

        binding.tvDetailTitle.text = title
        binding.tvDetailDescription.text = description
        binding.imgDetail.setImageResource(photoId?:0)

    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putString(DETAIL_TITLE, binding.tvDetailTitle.text.toString())
        outState.putString(DETAIL_DESCRIPTION, binding.tvDetailDescription.text.toString())
        outState.putString(DETAIL_PHOTO, binding.imgDetail.resources.toString())
    }
}