package com.fadhil.challenge.data


class ProcessResult<T>(result: Result<T>, delegate: ProcessResultDelegate<T>?) {
    init {
        when(result.status) {
            Result.Status.LOADING -> delegate?.loading()
            Result.Status.ERROR -> delegate?.error(result.message)
            Result.Status.UNAUTHORIZED -> delegate?.unAuthorize()
            Result.Status.SUCCESS -> delegate?.success(result.data)
        }
    }
}