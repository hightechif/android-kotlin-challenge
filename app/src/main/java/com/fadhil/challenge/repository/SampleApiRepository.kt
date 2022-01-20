package com.fadhil.challenge.repository

import com.fadhil.challenge.model.SampleApi
import com.fadhil.challenge.model.SampleApiDTO
import retrofit2.Call
import retrofit2.http.*

interface SampleApiRepository {
    @GET("/posts")
    fun getSampleAPI(): Call<ArrayList<SampleApi>>

    fun getComment(): Call<ArrayList<CommentResponse>>

    @FormUrlEncoded
    @POST("/posts")
    fun postSampleAPI(
        @Body
        @Field("userId") userId: Int,
        @Field("title") title: String,
        @Field("body") text: String,
    ): Call<SampleApiDTO>
}