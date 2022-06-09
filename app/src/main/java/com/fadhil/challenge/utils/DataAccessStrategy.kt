package com.fadhil.challenge.utils

//object DataAccessStrategy {
//    /**
//     * Data Access Strategy
//     * */
//    fun <T, A> performGetOperation(
//        databaseQuery: () -> LiveData<T>,
//        networkCall: suspend () -> Resource<A>,
//        saveCallResult: suspend (A) -> Unit
//    ): LiveData<Resource<T>> =
//        liveData(Dispatchers.IO) {
//            emit(Resource.Loading())
//            val source = databaseQuery.invoke().map { Resource.Success(it) }
//            emitSource(source)
//
//            val responseStatus = networkCall.invoke()
//            if (responseStatus.status == Resource.Status.SUCCESS) {
//                saveCallResult(responseStatus.data!!)
//
//            } else if (responseStatus.status == Resource.Status.ERROR) {
//                emit(Resource.Error(responseStatus.message!!))
//                emitSource(source)
//            }
//        }
//}

