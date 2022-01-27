package com.fadhil.challenge.adapter

import com.fadhil.challenge.data.room.student.Student

interface StudentOnDeleteOne {
    fun onItemClicked(data: Student)
}