package com.fadhil.challenge.view.callback

import com.fadhil.challenge.data.entities.Student

interface StudentDeleteOneCallback {
    fun onItemClicked(data: Student)
}