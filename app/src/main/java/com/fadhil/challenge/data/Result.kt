package com.fadhil.challenge.data

/**
 * OLD CODE
//data class Result<out T>(val status: Status, val data: T?, val message: String?) {
//
//    enum class Status {
//        SUCCESS,
//        ERROR,
//        LOADING,
//        UNAUTHORIZED
//    }
//
//    companion object {
//        fun <T> success(data: T): Result<T> {
//            return Result(Status.SUCCESS, data, null)
//        }
//
//        fun <T> error(message: String?, data: T? = null): Result<T> {
//            return Result(Status.ERROR, data, message)
//        }
//
//        fun <T> unauthorized(): Result<T> {
//            return Result(Status.UNAUTHORIZED, null, null)
//        }
//
//        fun <T> loading(data: T? = null): Result<T> {
//            return Result(Status.LOADING, data, null)
//        }
//    }
//}
 */

sealed class Result<out T>(val data: T? = null, val message: String? = null) {

    open var status: Status = Status.LOADING

    sealed class Status {
        object SUCCESS : Status()
        object ERROR : Status()
        object LOADING : Status()
        object UNAUTHORIZED : Status()
    }

    class Success<T>(data: T?) : Result<T>(data) {
        override var status: Status = Status.SUCCESS
    }

    class Error<T>(message: String?, data: T? = null) : Result<T>(data, message) {
        override var status: Status = Status.ERROR
    }

    class Loading<T>(data: T? = null) : Result<T>(data) {
        override var status: Status = Status.LOADING
    }

    class Unauthorized<T>() : Result<T>() {
        override var status: Status = Status.UNAUTHORIZED
    }
}
