package com.fadhil.challenge.data.source

import com.fadhil.challenge.data.Result
import com.fadhil.challenge.data.source.local.SessionLocalDataSource
import com.fadhil.challenge.data.source.remote.SessionRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

abstract class NetworkBoundResource<ResultType, RequestType>(
    private val sessionLocalDataSource: SessionLocalDataSource,
    private val sessionRemoteDataSource: SessionRemoteDataSource
) {
    private val result: Flow<Result<ResultType>> = flow {
        emit(Result.loading())
        val dbSource = loadFromDB()
        if (shouldFetch(dbSource)) {
            val response = createCall()
            when (response.status) {
                Result.Status.SUCCESS -> {
                    if (response.data != null) {
                        saveCallResult(response.data)
                        emit(
                            Result.success(
                                loadFromDB()
                            )
                        )
                    } else {
                        Result.error(
                            response.message,
                            loadFromDB()
                        )
                    }
                }
                Result.Status.UNAUTHORIZED -> {
                    val user = sessionLocalDataSource.getCached()
                    if (user?.refreshToken != null) {
                        val tokenResponse =
                            sessionRemoteDataSource.refreshToken(user.refreshToken!!)
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
                            loadFromDB()
                        )
                    )
                }
            }
        } else {
            emit(
                Result.success(
                    loadFromDB()
                )
            )
        }
    }

    protected abstract fun loadFromDB(): ResultType

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Result<RequestType>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<Result<ResultType>> = result

}