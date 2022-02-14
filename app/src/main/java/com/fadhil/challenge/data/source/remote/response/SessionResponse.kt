package com.fadhil.challenge.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class SessionResponse(
    @SerializedName("access_token")
    val token: String,

    @SerializedName("refresh_token")
    val refreshToken: String
)