package com.fadhil.challenge.ui.student

import com.fadhil.challenge.data.source.local.entity.Student

interface StudentDeleteOneCallback {
    fun onItemClicked(data: Student)
}