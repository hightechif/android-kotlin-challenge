package com.fadhil.challenge.presentation.heroes

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.fadhil.challenge.databinding.ActivityHeroDetailBinding

class HeroDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHeroDetailBinding

    companion object {
        const val HERO_NAME = "HERO_NAME"
        const val HERO_DESCRIPTION = "HERO_DESCRIPTION"
        const val HERO_PHOTO = "HERO_PHOTO"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Hero Detail Screen"

        val bundle: Bundle? = intent.extras
        val title = bundle?.getString(HERO_NAME)
        val description = bundle?.getString(HERO_DESCRIPTION)
        val photoId = bundle?.getInt(HERO_PHOTO)

        binding.tvDetailTitle.text = title
        binding.tvDetailDescription.text = description
        binding.imgDetail.setImageResource(photoId ?: 0)

    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putString(HERO_NAME, binding.tvDetailTitle.text.toString())
        outState.putString(HERO_DESCRIPTION, binding.tvDetailDescription.text.toString())
        outState.putString(HERO_PHOTO, binding.imgDetail.resources.toString())
    }
}