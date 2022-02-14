package com.fadhil.challenge.data.source.remote.request

import com.google.gson.annotations.SerializedName

data class RefreshTokenRequest (
    @SerializedName("refreshToken")
    val refreshToken: String
)