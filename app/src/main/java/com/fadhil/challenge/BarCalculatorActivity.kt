package com.fadhil.challenge

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.fadhil.challenge.databinding.ActivityBarCalculatorBinding

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
        binding.bar = bar
    }

    override fun onResume() {
        super.onResume()
        binding.btnCalculate.setOnClickListener {
            calculate()
        }
    }

    private fun calculate() {
        if (binding.etLength.text.isNotEmpty() && binding.etWidth.text.isNotEmpty() && binding.etDepth.text.toString().isNotEmpty()) {
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
        outState.putDouble("length", binding.etLength.text.toString().toDouble())
        outState.putDouble("width", binding.etWidth.text.toString().toDouble())
        outState.putDouble("depth", binding.etDepth.text.toString().toDouble())
        outState.putDouble("volume", binding.tvResultValue.text.toString().toDouble())
        super.onSaveInstanceState(outState)
    }
}