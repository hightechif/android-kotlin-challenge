package com.fadhil.challenge.data.source

import com.fadhil.challenge.data.NetworkBoundProcessResource
import com.fadhil.challenge.data.Result
import com.fadhil.challenge.data.source.local.LoginStateLocalDataSource
import com.fadhil.challenge.data.source.local.SessionLocalDataSource
import com.fadhil.challenge.data.source.remote.SessionRemoteDataSource
import com.fadhil.challenge.data.source.remote.response.SessionResponse
import com.fadhil.challenge.data.source.remote.response.base.BaseResponse
import com.fadhil.challenge.domain.model.Session
import com.fadhil.challenge.domain.repository.ISessionRepository
import com.fadhil.challenge.utils.DataMapperUser
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SessionRepository @Inject
constructor(
    private val sessionRemoteDataSource: SessionRemoteDataSource,
    private val sessionLocalDataSource: SessionLocalDataSource,
    private val loginStateLocalDataSource: LoginStateLocalDataSource
): ISessionRepository {

    override fun login(username: String, password: String): Flow<Result<Session?>> =
        object : NetworkBoundProcessResource<Session?, BaseResponse<SessionResponse>>(
            sessionLocalDataSource, sessionRemoteDataSource
        ) {
            override suspend fun createCall(): Result<BaseResponse<SessionResponse>> {
                return sessionRemoteDataSource.login(username, password)
            }

            override suspend fun saveCallResult(data: BaseResponse<SessionResponse>): Session? {
                val sessionData = DataMapperUser.loginMapResponseToDomain(data.data)
                if (data.data != null) {
                    sessionLocalDataSource.save(sessionData)
                    loginStateLocalDataSource.save(true)
                }
                return sessionData
            }
        }.asFlow()

    override fun logout(): Flow<Result<Any?>> {
        TODO("Not yet implemented")
    }

    override fun refreshToken() {
        TODO("Not yet implemented")
    }


}