package com.fadhil.challenge.ui.movies

import com.fadhil.challenge.data.source.local.entity.Movie

interface MovieCallback {
    fun onItemClicked(data: Movie)
}