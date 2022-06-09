package com.fadhil.challenge.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse (

    @SerializedName("id")
    var id: Long,

    @SerializedName("title")
    var title: String,

    @SerializedName("release_date")
    var release: String,

    @SerializedName("poster_path")
    var poster: String,

    @SerializedName("vote_average")
    var rating: Float,

    @SerializedName("overview")
    var overview: String
)