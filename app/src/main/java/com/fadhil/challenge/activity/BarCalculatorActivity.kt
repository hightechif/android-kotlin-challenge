package com.fadhil.challenge.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.fadhil.challenge.R
import com.fadhil.challenge.databinding.ActivityBarCalculatorBinding
import com.fadhil.challenge.model.Bar

class BarCalculatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBarCalculatorBinding
    private var bar: Bar = Bar(null, null, null, null)

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

        binding.btnCalculate.setOnClickListener {
            calculate()
        }

        binding.bar = bar
    }

    private fun calculate() {
        if (binding.etLength.text.isNotEmpty() && binding.etWidth.text.isNotEmpty() && binding.etDepth.text.isNotEmpty()) {
            bar.length = binding.etLength.text.toString().toDouble()
            bar.width = binding.etWidth.text.toString().toDouble()
            bar.depth = binding.etDepth.text.toString().toDouble()
            bar.volume = bar.length!! * bar.width!! * bar.depth!!
            binding.tvResultValue.text = bar.volume.toString()
        } else {
            Toast.makeText(applicationContext, "Please, fill all parameters!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        if (binding.etLength.text.isNotEmpty()) {
            outState.putDouble("length", binding.etLength.text.toString().toDouble())
        }
        if (binding.etWidth.text.isNotEmpty()) {
            outState.putDouble("width", binding.etWidth.text.toString().toDouble())
        }
        if (binding.etDepth.text.isNotEmpty()) {
            outState.putDouble("depth", binding.etDepth.text.toString().toDouble())
        }
        if (binding.tvResultValue.text.isNotEmpty()) {
            outState.putDouble("volume", binding.tvResultValue.text.toString().toDouble())
        }
        super.onSaveInstanceState(outState)
    }
}