package com.fadhil.challenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.fadhil.challenge.adapter.SampleApiAdapter
import com.fadhil.challenge.databinding.ActivitySampleApiBinding
import com.fadhil.challenge.model.SampleApi
import com.fadhil.challenge.model.SampleApiDTO
import com.fadhil.challenge.retrofit.SampleRetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SampleApiActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySampleApiBinding
    private val list: ArrayList<SampleApi> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySampleApiBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.rvPost.setHasFixedSize(true)
        binding.rvPost.layoutManager = LinearLayoutManager(this)


//        getAllData()
        createNewData()
    }

    private fun getAllData() {
        SampleRetrofitClient.instance.getSampleAPI().enqueue(object: Callback<ArrayList<SampleApi>>{
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

    private fun createNewData() {
        SampleRetrofitClient.instance.postSampleAPI(
            29,
            "Retrofit Fadhil",
            "A simple created data for POST method example"
        ).enqueue(object: Callback<SampleApiDTO> {
            override fun onResponse(call: Call<SampleApiDTO>, response: Response<SampleApiDTO>) {
                val responseData = "Response code: ${response.code()}\n" +
                        "title: ${response.body()?.title}\n" +
                        "body: ${response.body()?.text}\n" +
                        "userId: ${response.body()?.userId}\n" +
                        "id: ${response.body()?.id}\n"
                binding.tvResponseCode.text = responseData
            }

            override fun onFailure(call: Call<SampleApiDTO>, t: Throwable) {
                binding.tvResponseCode.text = t.message
            }

        })
    }
}