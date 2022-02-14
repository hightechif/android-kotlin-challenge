package com.fadhil.challenge.domain.model

data class Movie(
    var id: Long,
    var title: String,
    var release: String,
    var poster: String,
    var rating: Float,
    var overview: String
)
