package com.fadhil.challenge.utils

import com.fadhil.challenge.data.source.local.entity.MovieEntity
import com.fadhil.challenge.domain.model.Movie

object DataMapperMovie {

    fun mapMovieToDomain(input: MovieEntity) =
        Movie(
            id = input.id,
            title = input.title,
            release = input.release,
            poster = input.poster,
            rating = input.rating,
            overview = input.overview
        )

    fun mapMoviesToDomain(input: List<MovieEntity>) =
        input.map {
            mapMovieToDomain(it)
        }

}