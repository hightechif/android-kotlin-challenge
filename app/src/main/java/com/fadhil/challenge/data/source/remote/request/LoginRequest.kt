package com.fadhil.challenge.data.source.remote.request

import com.google.gson.annotations.SerializedName

data class LoginRequest (
    @SerializedName("nik")
    val nik: String?,
    @SerializedName("secret")
    val secret: String?,
    @SerializedName("deviceId")
    val deviceId: String?
)