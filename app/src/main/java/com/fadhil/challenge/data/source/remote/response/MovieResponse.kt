package com.fadhil.challenge.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse (
    var id: Long?,
    var title: String?,
    @field:SerializedName("release_date")
    var release: String?,
    @field:SerializedName("poster_path")
    var poster: String?,
    @field:SerializedName("vote_average")
    var rating: Float?,
    var overview: String?
)