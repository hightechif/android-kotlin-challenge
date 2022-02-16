package com.fadhil.challenge.domain.model

import com.fadhil.challenge.constant.Gender

data class Student (
    val id: Long,
    val name: String,
    val gender: Gender,
    val gpa: Float
)
