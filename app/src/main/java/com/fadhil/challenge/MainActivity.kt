package com.fadhil.challenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fadhil.challenge.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        buttonOnClickHandler()
    }

    private fun buttonOnClickHandler() {
        val button = binding.btnCalculatorCTA
        button.setOnClickListener {
            // Toast.makeText(applicationContext,"Button clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@MainActivity, BarCalculatorActivity::class.java)
            startActivity(intent)
        }
    }
}