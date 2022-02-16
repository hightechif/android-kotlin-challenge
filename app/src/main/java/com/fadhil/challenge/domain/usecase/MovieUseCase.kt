package com.fadhil.challenge.domain.usecase

import com.fadhil.challenge.data.source.remote.response.MovieResponse
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    fun getOrderList(reload: Boolean): Flow<Result<List<MovieResponse>?>>

}