package com.fadhil.challenge.presentation.movies

import com.fadhil.challenge.domain.model.Movie

interface ShareCallback {

    fun onShare(data: Movie)

}