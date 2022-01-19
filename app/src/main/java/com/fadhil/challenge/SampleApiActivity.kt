package com.fadhil.challenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.fadhil.challenge.adapter.SampleApiAdapter
import com.fadhil.challenge.databinding.ActivitySampleApiBinding
import com.fadhil.challenge.model.SampleApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SampleApiActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySampleApiBinding
    private val list = ArrayList<SampleApi>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySampleApiBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.rvPost.setHasFixedSize(true)
        binding.rvPost.layoutManager = LinearLayoutManager(this)

        RetrofitClient.instance.getPosts().enqueue(object: Callback<ArrayList<SampleApi>>{
            override fun onResponse(
                call: Call<ArrayList<SampleApi>>,
                response: Response<ArrayList<SampleApi>>
            ) {
                val responseCode = response.code().toString()
                binding.tvResponseCode.text = responseCode
                response.body()?.let { list.addAll(it) }
                val adapter = SampleApiAdapter(list)
                binding.rvPost.adapter = adapter
            }

            override fun onFailure(call: Call<ArrayList<SampleApi>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}