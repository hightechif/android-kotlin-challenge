package com.fadhil.challenge.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fadhil.challenge.databinding.ActivityMyViewBinding

class MyViewActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMyViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

}