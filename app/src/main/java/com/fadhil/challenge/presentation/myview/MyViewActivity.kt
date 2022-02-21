package com.fadhil.challenge.presentation.myview

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fadhil.challenge.databinding.ActivityMyViewBinding

class MyViewActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMyViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMyView.setOnClickListener {
            onClick()
        }
    }

    private fun onClick() {
        val message = "Tombol Beli ditekan"
        val toast = Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT)
        // toast.setGravity(Gravity.CENTER, 0, 0); # doesn't work anymore
        toast.show()
    }

}