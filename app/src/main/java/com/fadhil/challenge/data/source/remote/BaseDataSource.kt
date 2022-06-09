package com.fadhil.challenge.data.source.remote

import com.fadhil.challenge.constant.ErrorMessage
import com.fadhil.challenge.data.Result
import com.fadhil.challenge.data.source.remote.response.base.BaseResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.MalformedJsonException
import okio.BufferedSource
import retrofit2.Response
import timber.log.Timber
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.nio.charset.Charset

@Suppress("SpellCheckingInspection")
abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call()
            val code = response.code()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    return Result.Success(body)
                }
            }
            else {
                if (code == 401 || code == 403) {
                    return Result.Unauthorized()
                } else
                    if (code == 400 || code == 500) {
                        @Suppress("BlockingMethodInNonBlockingContext")
                        if (response.errorBody() != null) {
                            val bufferedSource: BufferedSource = response.errorBody()!!.source()
                            bufferedSource.request(Long.MAX_VALUE) // Buffer the entire body.

                            val json = bufferedSource.buffer.clone().readString(Charset.forName("UTF8"))

                            val badResponse = Gson().fromJson<BaseResponse<Any>?>(
                                json,
                                object : TypeToken<BaseResponse<Any>?>() {}.type
                            )
                            return when (badResponse.status) {
                                "OP207" -> error(badResponse.message)
                                else -> error(ErrorMessage.errorCode(badResponse.status))
                            }
                        }
                    }
                    else if (code == 503) {
                        return error(ErrorMessage.Http503)
                    }
            }
            return error(" $code ${response.message()}")
        } catch (e: Exception) {
            return if (e is ConnectException || e is UnknownHostException || e is MalformedJsonException || e is SocketTimeoutException || e is SocketException) {
                error(ErrorMessage.connectionError)
            } else {
                error(ErrorMessage.systemError)
            }
        }
    }

    private fun <T> error(message: String): Result<T> {
        Timber.e(message)
        return Result.Error("Network call has failed for a following reason: $message")
    }

}