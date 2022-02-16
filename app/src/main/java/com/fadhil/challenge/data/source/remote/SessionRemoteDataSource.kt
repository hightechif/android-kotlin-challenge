package com.fadhil.challenge.data.source.remote

import com.fadhil.challenge.data.source.remote.network.AuthService
import com.fadhil.challenge.data.source.remote.request.LoginRequest
import com.fadhil.challenge.data.source.remote.request.LogoutRequest
import com.fadhil.challenge.data.source.remote.request.RefreshTokenRequest

class SessionRemoteDataSource(
    private val authService: AuthService
) : BaseDataSource() {

    suspend fun login(nik: String?, password: String?, deviceId: String?) =
        getResult {
            authService.login(
                LoginRequest(nik, password, deviceId)
            )
        }

    suspend fun logout(deviceId: String?) =
        getResult {
            authService.logout(
                LogoutRequest(
                    deviceId
                )
            )
        }

    suspend fun refreshToken(refreshToken: String) =
        getResult {
            authService.refreshToken(
                RefreshTokenRequest(refreshToken)
            )
        }

}