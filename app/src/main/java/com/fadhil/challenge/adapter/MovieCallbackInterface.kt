package com.fadhil.challenge.adapter

import com.fadhil.challenge.model.Movie

interface MovieCallbackInterface {
    fun onItemClicked(data: Movie)
}