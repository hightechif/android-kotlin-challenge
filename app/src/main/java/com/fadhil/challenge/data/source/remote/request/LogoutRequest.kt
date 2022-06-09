package com.fadhil.challenge.data.source.remote.request

import com.google.gson.annotations.SerializedName

data class LogoutRequest (
    @SerializedName("deviceId")
    val deviceId: String?
)