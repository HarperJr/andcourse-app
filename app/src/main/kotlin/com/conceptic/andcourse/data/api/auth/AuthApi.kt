package com.conceptic.andcourse.data.api.auth

import com.conceptic.andcourse.data.api.auth.model.JwtTokenResponse
import com.conceptic.andcourse.data.api.auth.model.SignInRequest
import com.conceptic.andcourse.data.api.auth.model.SignUpRequest
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/signup")
    fun signUp(@Body signUpRequest: SignUpRequest): Single<Response<Nothing>>

    @POST("/signin")
    fun signIn(@Body signInRequest: SignInRequest): Single<Response<JwtTokenResponse>>
}