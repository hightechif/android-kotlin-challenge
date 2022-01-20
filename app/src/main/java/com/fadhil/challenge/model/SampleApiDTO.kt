package com.fadhil.challenge.model

import com.google.gson.annotations.SerializedName

data class SampleApiDTO (
    val userId: String?,
    var id: Int,
    var title: String?,
    @SerializedName("body")
    var text: String?
)