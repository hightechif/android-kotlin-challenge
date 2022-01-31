package com.fadhil.challenge.data.remote.response

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    val page: Int = 0,
    val results: List<T> = listOf(),
    @SerializedName("total_results")
    val totalResults: Int = 0,
    @SerializedName("total_pages")
    val totalPages: Int = 0,
)