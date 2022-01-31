package com.fadhil.challenge.view.callback

import com.fadhil.challenge.data.source.local.entity.Student

interface StudentDeleteOneCallback {
    fun onItemClicked(data: Student)
}