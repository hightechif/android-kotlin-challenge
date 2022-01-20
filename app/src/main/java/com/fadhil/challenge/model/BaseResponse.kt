package com.fadhil.challenge.model

import com.google.gson.annotations.SerializedName

class BaseResponse {
    val page: String = ""
    val results: ArrayList<Movie> = arrayListOf()
    @SerializedName("total_results")
    val totalResults: Int = 0
    @SerializedName("total_pages")
    val totalPages: Int = 0
}