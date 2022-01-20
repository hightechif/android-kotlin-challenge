package com.fadhil.challenge.model

import com.google.gson.annotations.SerializedName

data class Movie (
    var id: Long = 0,
    var title: String = "",
    @SerializedName("release_date")
    var release: String = "",
    @SerializedName("poster_path")
    var poster: String = "",
    @SerializedName("vote_average")
    var rating: Float = 0F,
    var overview: String = ""
)
