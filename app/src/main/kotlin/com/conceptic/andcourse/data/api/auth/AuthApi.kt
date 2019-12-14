package com.conceptic.andcourse.data.api.auth

import com.conceptic.andcourse.data.api.auth.model.CredentialsResponse
import com.conceptic.andcourse.data.api.auth.model.JwtTokenResponse
import com.conceptic.andcourse.data.api.auth.model.SignInRequest
import com.conceptic.andcourse.data.api.auth.model.SignUpRequest
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {
    @POST("/signup")
    fun signUpAsync(@Body signUpRequest: SignUpRequest): Deferred<Response<Unit>>

    @POST("/signin")
    fun signInAsync(@Body signInRequest: SignInRequest): Deferred<Response<JwtTokenResponse>>

    @GET("/credentials")
    fun credentialsAsync(): Deferred<Response<CredentialsResponse>>
}