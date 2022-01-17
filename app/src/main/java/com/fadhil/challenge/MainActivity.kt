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
    }

    override fun onResume() {
        super.onResume()
        binding.btnCalculatorCTA.setOnClickListener{
            openCalculator()
        }
        binding.btnCameraCTA.setOnClickListener {
            openCamera()
        }
        binding.btnMyViewAppCTA.setOnClickListener {
            openMyViewApp()
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
}