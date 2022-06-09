package com.fadhil.challenge.data.source.remote.response.base

import com.google.gson.annotations.SerializedName

data class PageableData<T>(
    val page: Int,
    val results: List<T>,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
)