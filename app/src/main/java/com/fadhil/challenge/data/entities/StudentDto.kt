package com.fadhil.challenge.data.entities

import com.fadhil.challenge.constant.Gender

data class StudentDto(
    val id: Int = 0,
    val name: String,
    val gender: Gender,
    val gpa: Float
)