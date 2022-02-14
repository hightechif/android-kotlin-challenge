package com.fadhil.challenge.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fadhil.challenge.constant.Gender

@Entity(tableName="student")
data class StudentEntity(

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "gender")
    val gender: Gender,

    @ColumnInfo(name = "gpa")
    val gpa: Float

)
