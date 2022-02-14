package com.fadhil.challenge.data.source.remote.network

import com.fadhil.challenge.data.source.remote.request.LoginRequest
import com.fadhil.challenge.data.source.remote.request.LogoutRequest
import com.fadhil.challenge.data.source.remote.request.RefreshTokenRequest
import com.fadhil.challenge.data.source.remote.response.SessionResponse
import com.fadhil.challenge.data.source.remote.response.base.BaseResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("driver/api/mobile/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<BaseResponse<SessionResponse>>

    @POST("driver/api/mobile/logout")
    suspend fun logout(@Body logoutRequest: LogoutRequest): Response<BaseResponse<Any>>

    @POST("driver/api/mobile/refresh")
    suspend fun refreshToken(@Body refreshTokenRequest: RefreshTokenRequest): Response<BaseResponse<SessionResponse>>

}