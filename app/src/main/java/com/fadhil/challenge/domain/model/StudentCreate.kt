package com.fadhil.challenge.domain.model

import com.fadhil.challenge.constant.Gender

data class StudentCreate (
    val name: String,
    val gender: Gender,
    val gpa: Float
)
