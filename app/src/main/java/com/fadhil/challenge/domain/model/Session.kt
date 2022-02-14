package com.fadhil.challenge.domain.model

data class Session(
    var token: String?,
    var refreshToken: String?
)