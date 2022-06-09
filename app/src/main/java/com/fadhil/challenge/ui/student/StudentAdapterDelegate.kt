package com.fadhil.challenge.ui.student

import com.fadhil.challenge.domain.model.Student

interface StudentAdapterDelegate {
    fun onItemClicked(data: Student)
}