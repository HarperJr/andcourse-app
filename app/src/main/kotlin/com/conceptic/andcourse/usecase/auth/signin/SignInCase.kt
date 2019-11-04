package com.conceptic.andcourse.usecase.auth.signin

import com.conceptic.andcourse.data.api.auth.AuthApiExecutor
import com.conceptic.andcourse.data.api.auth.JwtTokenProvider
import com.conceptic.andcourse.data.api.auth.model.SignInRequest
import com.conceptic.andcourse.usecase.UseCase
import io.reactivex.Observable

class SignInCase(
    private val authApiExecutor: AuthApiExecutor,
    private val jwtTokenProvider: JwtTokenProvider
) : UseCase<SignInParams, Unit> {
    override fun execute(param: SignInParams): Observable<Unit> = authApiExecutor
        .signIn(SignInRequest(email = param.email, password = param.password))
        .map {
            jwtTokenProvider.put(it.jwt)
        }
}