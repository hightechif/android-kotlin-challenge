package com.fadhil.challenge.view.ui

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.fadhil.challenge.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalculatorCTA.setOnClickListener(this::openCalculator)
        binding.btnCameraCTA.setOnClickListener(this::openCamera)
        binding.btnMyViewAppCTA.setOnClickListener(this::openMyViewApp)
        binding.btnHeroesCTA.setOnClickListener(this::openHeroesApp)
        binding.btnMoviesCTA.setOnClickListener(this::openMoviesApp)
        binding.btnStudentCTA.setOnClickListener(this::openStudentApp)
    }

    private fun openCalculator(view: View?) {
        val calculatorIntent = Intent(view?.context, BarCalculatorActivity::class.java)
        startActivity(calculatorIntent)
    }

    private fun openCamera(view: View?) {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivity(takePictureIntent)
    }

    private fun openMyViewApp(view: View?) {
        val myViewIntent = Intent(view?.context, MyViewActivity::class.java)
        startActivity(myViewIntent)
    }

    private fun openHeroesApp(view: View?) {
        val heroesIntent = Intent(view?.context, HeroesActivity::class.java)
        startActivity(heroesIntent)
    }

    private fun openMoviesApp(view: View?) {
        val moviesIntent = Intent(view?.context, MoviesActivity::class.java)
        startActivity(moviesIntent)
    }

    private fun openStudentApp(view: View?) {
        val studentIntent = Intent(view?.context, StudentActivity::class.java)
        startActivity(studentIntent)
    }

}