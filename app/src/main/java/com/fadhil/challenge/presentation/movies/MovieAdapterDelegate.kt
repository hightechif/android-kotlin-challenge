package com.fadhil.challenge.presentation.movies

import com.fadhil.challenge.domain.model.Movie

interface MovieAdapterDelegate {

    fun onItemClicked(data: Movie)

    fun onShare(data: Movie)

}