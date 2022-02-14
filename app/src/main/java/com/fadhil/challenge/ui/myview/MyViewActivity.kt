package com.fadhil.challenge.ui.myview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fadhil.challenge.databinding.ActivityMyViewBinding

class MyViewActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMyViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}