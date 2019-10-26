package com.conceptic.andcourse.data.api.auth

import com.conceptic.andcourse.data.api.auth.model.JwtTokenResponse
import com.conceptic.andcourse.data.api.auth.model.SingUpRequest
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.POST

interface AuthApi {
    @POST
    fun signIn(): Single<JwtTokenResponse>

    @POST
    fun signUp(singUpRequest: SingUpRequest): Completable
}