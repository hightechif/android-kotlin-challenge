package com.fadhil.challenge.utils

import com.fadhil.challenge.data.source.remote.response.MovieResponse
import com.fadhil.challenge.domain.model.Movie

object DataMapperMovie {

    fun mapMovieToDomain(input: MovieResponse) =
        Movie(
            id = input.id,
            title = input.title,
            release = input.release,
            poster = input.poster,
            rating = input.rating,
            overview = input.overview
        )

    fun mapMoviesToDomain(input: List<MovieResponse>) =
        input.map {
            mapMovieToDomain(it)
        }

}