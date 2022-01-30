package com.fadhil.challenge.adapter

import com.fadhil.challenge.data.source.local.entity.Student

interface StudentOnDeleteOne {
    fun onItemClicked(data: Student)
}