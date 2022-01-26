package com.fadhil.challenge.model

import com.fadhil.challenge.constant.Gender

data class StudentDto(
    val id: Int = 0,
    val name: String,
    val gender: Gender,
    val gpa: Float
)