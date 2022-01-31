package com.fadhil.challenge.view.callback

import com.fadhil.challenge.model.Movie

interface MovieCallback {
    fun onItemClicked(data: Movie)
}