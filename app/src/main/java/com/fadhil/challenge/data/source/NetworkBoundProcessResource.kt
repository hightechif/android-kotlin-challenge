package com.fadhil.challenge.data.source

import com.fadhil.challenge.data.Result
import com.fadhil.challenge.data.source.local.SessionLocalDataSource
import com.fadhil.challenge.data.source.remote.SessionRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

abstract class NetworkBoundProcessResource<ResultType, RequestType>(
    private val sessionLocalDataSource: SessionLocalDataSource,
    private val sessionRemoteDataSource: SessionRemoteDataSource
) {

    private val result: Flow<Result<ResultType>> = flow {
        emit(Result.loading())
        val response = createCall()
        when (response.status) {
            Result.Status.SUCCESS -> {
                val responseData = saveCallResult(response.data!!)
                emit(Result.success(responseData))
            }
            Result.Status.UNAUTHORIZED -> {
                val user = sessionLocalDataSource.getCached()
                if (user?.refreshToken != null) {
                    val tokenResponse = sessionRemoteDataSource.refreshToken(user.refreshToken!!)
                    when (tokenResponse.status) {
                        Result.Status.SUCCESS -> {
                            if (tokenResponse.data?.data != null &&
                                tokenResponse.data.isSuccess()
                            ) {
                                user.token = tokenResponse.data.data.token
                                user.refreshToken = tokenResponse.data.data.refreshToken
                                sessionLocalDataSource.save(user)
                            }
                            emitAll(asFlow())
                        }
                        else -> {
                            emit(Result.unauthorized<ResultType>())
                        }
                    }
                } else emit(Result.unauthorized<ResultType>())
            }
            else -> {
                emit(
                    Result.error(
                        response.message,
                        null
                    )
                )
            }
        }
    }

    protected abstract suspend fun createCall(): Result<RequestType>

    protected abstract suspend fun saveCallResult(data: RequestType): ResultType

    fun asFlow(): Flow<Result<ResultType>> = result

}