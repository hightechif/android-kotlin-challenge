package com.fadhil.challenge.presentation.barcalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.fadhil.challenge.R
import com.fadhil.challenge.databinding.ActivityBarCalculatorBinding
import com.fadhil.challenge.domain.model.Bar

class BarCalculatorActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityBarCalculatorBinding
    private lateinit var edtLength: EditText
    private lateinit var edtWidth: EditText
    private lateinit var edtDepth: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView

    companion object {
        private val bar: Bar = Bar(null, null, null)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bar_calculator)

        edtLength = binding.etLength
        edtWidth = binding.etWidth
        edtDepth = binding.etDepth
        btnCalculate = binding.btnCalculate
        tvResult = binding.tvResultValue

        if (savedInstanceState != null) {
            // Then the application is being reloaded
            bar.length = savedInstanceState.getDouble("length")
            bar.width = savedInstanceState.getDouble("width")
            bar.depth = savedInstanceState.getDouble("depth")
            tvResult.text = savedInstanceState.getString("volume")
        }

        binding.bar = bar
        btnCalculate.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btnCalculate) {
            val inputLength = edtLength.text.toString().trim()
            val inputWidth = edtWidth.text.toString().trim()
            val inputDepth = edtDepth.text.toString().trim()
            var isEmptyFields = false
            if (inputLength.isEmpty()) {
                isEmptyFields = true
                edtLength.error = "Field ini tidak boleh kosong"
            }
            if (inputWidth.isEmpty()) {
                isEmptyFields = true
                edtWidth.error = "Field ini tidak boleh kosong"
            }
            if (inputDepth.isEmpty()) {
                isEmptyFields = true
                edtDepth.error = "Field ini tidak boleh kosong"
            }

            bar.length = inputLength.toDoubleOrNull()
            bar.width = inputWidth.toDoubleOrNull()
            bar.depth = inputDepth.toDoubleOrNull()

            if (!isEmptyFields) {
                tvResult.text = (bar.length!! * bar.width!! * bar.depth!!).toString()
            }
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
        outState.putString("volume", tvResult.text.toString())
    }
}