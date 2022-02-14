package com.fadhil.challenge.presentation

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fadhil.challenge.databinding.ActivityMainBinding
import com.fadhil.challenge.presentation.barcalculator.BarCalculatorActivity
import com.fadhil.challenge.presentation.heroes.HeroesActivity
import com.fadhil.challenge.presentation.movies.MoviesActivity
import com.fadhil.challenge.presentation.myview.MyViewActivity
import com.fadhil.challenge.presentation.student.StudentActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkPlatformVersion()

        binding.btnCalculatorCTA.setOnClickListener(this::openCalculator)
        binding.btnCameraCTA.setOnClickListener(this::openCamera)
        binding.btnMyViewAppCTA.setOnClickListener(this::openMyViewApp)
        binding.btnHeroesCTA.setOnClickListener(this::openHeroesApp)
        binding.btnMoviesCTA.setOnClickListener(this::openMoviesApp)
        binding.btnStudentCTA.setOnClickListener(this::openStudentApp)
    }

    private fun checkPlatformVersion() {
        Timber.i("SDK: ${Build.VERSION.SDK_INT}")
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            // Menggunakan versi di bawah 26
            Timber.i("Disabled feature, because version SDK is lower than 26")
        }
    }

    private fun openCalculator(view: View?) {
        val calculatorIntent = Intent(view?.context, BarCalculatorActivity::class.java)
        startActivity(calculatorIntent)
    }

    private fun openCamera(view: View?) {
        val isCameraFeatureExist = checkCamera()
        if (isCameraFeatureExist) {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(takePictureIntent)
        } else {
            Toast.makeText(view?.context, "Your device doesn't have any camera", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkCamera(): Boolean {
        return packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)
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