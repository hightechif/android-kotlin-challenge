package com.fadhil.challenge.presentation.student

import com.fadhil.challenge.domain.model.Student

interface StudentDeleteOneCallback {
    fun onItemClicked(data: Student)
}