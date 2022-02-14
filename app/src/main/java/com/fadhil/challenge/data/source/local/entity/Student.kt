package com.fadhil.challenge.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fadhil.challenge.constant.Gender

@Entity(tableName="student")
data class Student(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @NonNull
    @ColumnInfo(name = "name")
    val name: String,
    @NonNull
    @ColumnInfo(name = "gender")
    val gender: Gender,
    @NonNull
    @ColumnInfo(name = "gpa")
    val gpa: Float
)
