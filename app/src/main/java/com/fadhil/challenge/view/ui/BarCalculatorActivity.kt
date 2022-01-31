package com.fadhil.challenge.view.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.fadhil.challenge.R
import com.fadhil.challenge.databinding.ActivityBarCalculatorBinding
import com.fadhil.challenge.data.entities.Bar

class BarCalculatorActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityBarCalculatorBinding

    companion object {
        private val bar: Bar = Bar(null, null, null, null)
    }

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
        binding.btnCalculate.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (binding.etLength.text.isNotEmpty() && binding.etWidth.text.isNotEmpty() && binding.etDepth.text.isNotEmpty()) {
            bar.length = binding.etLength.text.toString().trim().toDouble()
            bar.width = binding.etWidth.text.toString().trim().toDouble()
            bar.depth = binding.etDepth.text.toString().trim().toDouble()
            bar.volume = bar.length!! * bar.width!! * bar.depth!!
            binding.tvResultValue.text = bar.volume.toString()
        } else {
            Toast.makeText(applicationContext, "Please, fill all parameters!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (binding.etLength.text.isNotEmpty()) {
            outState.putDouble("length", binding.etLength.text.toString().trim().toDouble())
        }
        if (binding.etWidth.text.isNotEmpty()) {
            outState.putDouble("width", binding.etWidth.text.toString().trim().toDouble())
        }
        if (binding.etDepth.text.isNotEmpty()) {
            outState.putDouble("depth", binding.etDepth.text.toString().trim().toDouble())
        }
        if (binding.tvResultValue.text.isNotEmpty()) {
            outState.putDouble("volume", binding.tvResultValue.text.toString().trim().toDouble())
        }
    }
}