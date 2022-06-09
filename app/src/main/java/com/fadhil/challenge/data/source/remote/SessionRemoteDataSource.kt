package com.fadhil.challenge.data.source.remote

import com.fadhil.challenge.data.source.remote.network.AuthService
import com.fadhil.challenge.data.source.remote.request.LoginRequest
import com.fadhil.challenge.data.source.remote.request.LogoutRequest
import com.fadhil.challenge.data.source.remote.request.RefreshTokenRequest
import javax.inject.Inject

class SessionRemoteDataSource @Inject
constructor(
    private val authService: AuthService
) : BaseDataSource() {

    suspend fun login(username: String, password: String) =
        getResult {
            authService.login(
                LoginRequest(username, password)
            )
        }

    suspend fun logout(deviceId: String?) =
        getResult {
            authService.logout(
                LogoutRequest(deviceId)
            )
        }

    suspend fun refreshToken(refreshToken: String) =
        getResult {
            authService.refreshToken(
                RefreshTokenRequest(refreshToken)
            )
        }

}