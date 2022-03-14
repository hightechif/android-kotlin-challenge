package com.fadhil.challenge.presentation.student

import com.fadhil.challenge.domain.model.Student

interface StudentAdapterDelegate {
    fun onItemClicked(data: Student)
}