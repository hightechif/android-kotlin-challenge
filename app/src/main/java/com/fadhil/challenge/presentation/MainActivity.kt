package com.fadhil.challenge.presentation

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fadhil.challenge.R
import com.fadhil.challenge.databinding.ActivityMainBinding
import com.fadhil.challenge.presentation.barcalculator.BarCalculatorActivity
import com.fadhil.challenge.presentation.heroes.HeroesActivity
import com.fadhil.challenge.presentation.movies.MoviesActivity
import com.fadhil.challenge.presentation.myview.MyViewActivity
import com.fadhil.challenge.presentation.student.StudentActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkPlatformVersion()

        binding.btnCalculatorCTA.setOnClickListener(this)
        binding.btnCameraCTA.setOnClickListener(this)
        binding.btnMyViewAppCTA.setOnClickListener(this)
        binding.btnHeroesCTA.setOnClickListener(this)
        binding.btnMoviesCTA.setOnClickListener(this)
        binding.btnStudentCTA.setOnClickListener(this)
    }

    private fun checkPlatformVersion() {
        Timber.i("SDK: ${Build.VERSION.SDK_INT}")
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            // Menggunakan versi di bawah 26
            Timber.i("Disabled feature, because version SDK is lower than 26")
        }
    }

    private fun checkCamera(): Boolean {
        return packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnCalculatorCTA -> {
                val calculatorIntent = Intent(this@MainActivity, BarCalculatorActivity::class.java)
                startActivity(calculatorIntent)
            }

            R.id.btnCameraCTA -> {
                val isCameraFeatureExist = checkCamera()
                if (isCameraFeatureExist) {
                    val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivity(takePictureIntent)
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Your device doesn't have any camera",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            R.id.btnMyViewAppCTA -> {
                val myViewIntent = Intent(this@MainActivity, MyViewActivity::class.java)
                startActivity(myViewIntent)
            }

            R.id.btnHeroesCTA -> {
                val heroesIntent = Intent(this@MainActivity, HeroesActivity::class.java)
                startActivity(heroesIntent)
            }

            R.id.btnMoviesCTA -> {
                val moviesIntent = Intent(this@MainActivity, MoviesActivity::class.java)
                startActivity(moviesIntent)
            }

            R.id.btnStudentCTA -> {
                val studentIntent = Intent(this@MainActivity, StudentActivity::class.java)
                startActivity(studentIntent)
            }
        }
    }

}