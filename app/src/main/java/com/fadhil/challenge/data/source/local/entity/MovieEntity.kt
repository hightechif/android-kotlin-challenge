package com.fadhil.challenge.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class MovieEntity(

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    var id: Long,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "release_date")
    var release: String,

    @ColumnInfo(name = "poster_path")
    var poster: String,

    @ColumnInfo(name = "vote_average")
    var rating: Float,

    @ColumnInfo(name = "overview")
    var overview: String

)
