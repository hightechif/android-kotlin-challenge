package com.fadhil.challenge.data

interface ProcessResultDelegate<T> {
    fun loading()
    fun error(message: String?)
    fun unAuthorize()
    fun success(data: T?)
}