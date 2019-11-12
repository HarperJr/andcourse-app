package com.conceptic.andcourse.data.api

import com.conceptic.andcourse.data.api.support.ErrorMessage
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Response

/**
 * Base class for all ApiExecutors
 */
open class ApiExecutor<T>(private val api: T) : KoinComponent {
    private val gson by inject<Gson>()

    /**
     * This method consumes any api method invocation and returns it's body mapped to response class
     *
     * @param serviceInvocation invocation of api service, throws retrofit response with model
     * @exception ApiException if response code is 4xx, 5xx
     */
    protected suspend fun <E> executeService(serviceInvocation: T.() -> Deferred<Response<E>>): E {
        val response = serviceInvocation.invoke(api).await()
        return if (response.isSuccessful) {
            response.body() ?: throw ApiException(response.code(), "Response is null")
        } else {
            response.errorBody()?.let { errorBody ->
                val body = gson.fromJson(errorBody.string(), ErrorMessage::class.java)
                throw ApiException(response.code(), body.message)
            } ?: throw ApiException(response.code(), response.message())
        }
    }
}