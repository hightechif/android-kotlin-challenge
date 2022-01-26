package com.fadhil.challenge.data.room.student

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fadhil.challenge.constant.Gender

@Entity(tableName="student")
data class Student(

    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @NonNull
    var name: String,

    @NonNull
    var gender: Gender,

    @NonNull
    var gpa: Float
)
