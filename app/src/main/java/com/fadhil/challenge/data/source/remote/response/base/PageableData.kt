package com.fadhil.challenge.data.source.remote.response.base

import com.google.gson.annotations.SerializedName

data class PageableData<T>(
    val page: Int = 0,
    val results: List<T> = listOf(),
    @SerializedName("total_results")
    val totalResults: Int = 0,
    @SerializedName("total_pages")
    val totalPages: Int = 0,
)