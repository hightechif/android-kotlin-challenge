package com.fadhil.challenge.utils

import com.fadhil.challenge.data.source.remote.response.SessionResponse
import com.fadhil.challenge.domain.model.Session

object DataMapperUser {

    fun loginMapResponseToDomain(input: SessionResponse?) =
        input?.let {
            Session(
                token = input.token,
                refreshToken = input.refreshToken
            )
        }

}