package com.fadhil.challenge.domain.usecase.session

import com.fadhil.challenge.data.Result
import com.fadhil.challenge.domain.model.Session
import kotlinx.coroutines.flow.Flow

interface SessionUseCase {

    fun login(username: String, password: String): Flow<Result<Session?>>

    fun logout(): Flow<Result<Any?>>

}