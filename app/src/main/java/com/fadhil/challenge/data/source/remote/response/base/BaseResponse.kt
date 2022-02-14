package com.fadhil.challenge.data.source.remote.response.base

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @field:SerializedName("data")
    val data: T?,
    @field:SerializedName("message")
    val message: String,
    @field:SerializedName("status")
    val status: String,
    @field:SerializedName("timestamp")
    val timeStamp: String
) {
    fun isSuccess() = "00" == status || "01" == status
    fun errorCode() = if ("02" == status || "03" == status) message else status
}