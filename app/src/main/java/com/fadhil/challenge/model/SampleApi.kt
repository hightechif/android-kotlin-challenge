package com.fadhil.challenge.model

import com.google.gson.annotations.SerializedName

data class SampleApi(
    var id: Int,
    var title: String?,
    @SerializedName("body")
    var text: String?
)
