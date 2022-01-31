package com.fadhil.challenge.view.callback

import com.fadhil.challenge.data.entities.Movie

interface MovieCallback {
    fun onItemClicked(data: Movie)
}