package com.fadhil.challenge.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class Movie (
    @PrimaryKey(autoGenerate = true)
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
