package com.fadhil.challenge.adapter

import com.fadhil.challenge.model.StudentDto

interface StudentCallbackInterface {
    fun onItemClicked(data: StudentDto)
}