package com.conceptic.andcourse.data.api

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response

/**
 * Base class for all ApiExecutors
 */
open class ApiExecutor<T>(private val api: T) {
    /**
     * This method consumes any api method invocation and throws it's mapped response wrapped into rx java observable
     *
     * @param serviceInvocation invocation of api service, throws retrofit response with model
     * @exception ApiException if response code is 4xx, 5xx
     * @exception Exception is response is null
     */
    protected fun <E> executeService(serviceInvocation: T.() -> Single<Response<E>>): Observable<E> {
        return serviceInvocation.invoke(api)
            .map { RetrofitResponseMapper.map(it) }
            .toObservable()
    }
}