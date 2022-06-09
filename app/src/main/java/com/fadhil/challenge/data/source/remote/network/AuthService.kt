package com.fadhil.challenge.data.source.remote.network

import com.fadhil.challenge.data.source.remote.request.LoginRequest
import com.fadhil.challenge.data.source.remote.request.LogoutRequest
import com.fadhil.challenge.data.source.remote.request.RefreshTokenRequest
import com.fadhil.challenge.data.source.remote.response.SessionResponse
import com.fadhil.challenge.data.source.remote.response.base.BaseResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Url

interface AuthService {

    @POST("/api/v1/auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<BaseResponse<SessionResponse>>

    @POST("/api/v1/auth/logout")
    suspend fun logout(@Body logoutRequest: LogoutRequest): Response<BaseResponse<Any>>

    @POST("/api/v1/auth/refresh")
    suspend fun refreshToken(@Body refreshTokenRequest: RefreshTokenRequest): Response<BaseResponse<SessionResponse>>

}