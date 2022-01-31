package com.fadhil.challenge

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.fadhil.challenge.databinding.ActivityMainBinding
import com.fadhil.challenge.view.ui.*
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

    private fun openCalculator(v: View?) {
        val calculatorIntent = Intent(this, BarCalculatorActivity::class.java)
        startActivity(calculatorIntent)
    }

    private fun openCamera(v: View?) {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivity(takePictureIntent)
    }

    private fun openMyViewApp(v: View?) {
        val myViewIntent = Intent(this, MyViewActivity::class.java)
        startActivity(myViewIntent)
    }

    private fun openHeroesApp(v: View?) {
        val heroesIntent = Intent(this, HeroesActivity::class.java)
        startActivity(heroesIntent)
    }

    private fun openMoviesApp(v: View?) {
        val moviesIntent = Intent(this, MoviesActivity::class.java)
        startActivity(moviesIntent)
    }

    private fun openStudentApp(v: View?) {
        val studentIntent = Intent(this, StudentActivity::class.java)
        startActivity(studentIntent)
    }

}