package com.fadhil.challenge.presentation.movies

import com.fadhil.challenge.domain.model.Movie

interface MovieCallback {
    fun onItemClicked(data: Movie)
}