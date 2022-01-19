package com.fadhil.challenge

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.fadhil.challenge.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnCalculatorCTA.setOnClickListener{
            openCalculator()
        }
        binding.btnCameraCTA.setOnClickListener {
            openCamera()
        }
        binding.btnMyViewAppCTA.setOnClickListener {
            openMyViewApp()
        }
        binding.btnHeroesCTA.setOnClickListener {
            openHeroesApp()
        }
        binding.btnSampleAPI.setOnClickListener {
            openSampleAPI()
        }
    }

    private fun openCalculator() {
        val activityIntent = Intent(this, BarCalculatorActivity::class.java)
        startActivity(activityIntent)
    }

    private fun openCamera() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivity(takePictureIntent)
    }

    private fun openMyViewApp() {
        val myViewIntent = Intent(this, MyViewActivity::class.java)
        startActivity(myViewIntent)
    }

    private fun openHeroesApp() {
        val heroesIntent = Intent(this, HeroesActivity::class.java)
        startActivity(heroesIntent)
    }

    private fun openSampleAPI() {
        val sampleApiIntent = Intent(this, SampleApiActivity::class.java)
        startActivity(sampleApiIntent)
    }

}