package com.fadhil.challenge

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fadhil.challenge.databinding.ActivityBarCalculatorBinding

class BarCalculatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBarCalculatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBarCalculatorBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        calculateButtonOnClickHandler()
    }

    private fun calculateButtonOnClickHandler() {
        val button = binding.btnCalculate
        button.setOnClickListener {
            Toast.makeText(applicationContext, "Calculate button clicked", Toast.LENGTH_SHORT)
                .show()
        }
    }
}