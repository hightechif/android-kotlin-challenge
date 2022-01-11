package com.fadhil.challenge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.fadhil.challenge.databinding.ActivityBarCalculatorBinding

class BarCalculatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBarCalculatorBinding
    private val bar: Bar = Bar()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bar_calculator)

        if (savedInstanceState != null) {
            // Then the application is being reloaded
            bar.length = savedInstanceState.getDouble("length")
            bar.width = savedInstanceState.getDouble("width")
            bar.depth = savedInstanceState.getDouble("depth")
            bar.volume = savedInstanceState.getDouble("volume")
        }

        binding.bar = bar
        binding.btnCalculate.setOnClickListener {
            calculate()
        }
    }

    private fun calculate() {
        binding.apply {
            bar?.length = etLength.text.toString().toDouble()
            bar?.width = etWidth.text.toString().toDouble()
            bar?.depth = etDepth.text.toString().toDouble()
            val volume = bar?.length!! * bar?.width!! * bar?.depth!!
            invalidateAll()
            bar?.volume = volume
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putDouble("length", bar.length)
        outState.putDouble("width", bar.width)
        outState.putDouble("depth", bar.depth)
        outState.putDouble("volume", bar.volume)
        super.onSaveInstanceState(outState)
    }
}