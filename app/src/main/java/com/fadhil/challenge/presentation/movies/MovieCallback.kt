package com.fadhil.challenge.presentation.movies

import com.fadhil.challenge.data.source.local.entity.MovieEntity

interface MovieCallback {
    fun onItemClicked(data: MovieEntity)
}