package com.fadhil.challenge

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.fadhil.challenge.databinding.ActivityBarCalculatorBinding

class BarCalculatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBarCalculatorBinding
    private val bar: Bar = Bar()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bar_calculator)

        binding.btnCalculate.setOnClickListener {
            calculate()
        }

        binding.bar = bar
    }

    private fun calculate() {
        binding.apply {
            bar?.length = etLength.text.toString()
            bar?.width = etWidth.text.toString()
            bar?.depth = etDepth.text.toString()
            val volume = bar?.length?.toDouble()!! * bar?.width?.toDouble()!! * bar?.depth?.toDouble()!!
            invalidateAll()
            bar?.volume = volume.toString().toDouble()
            tvResultValue.text = volume.toString()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }
}