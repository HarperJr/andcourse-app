package com.conceptic.andcourse.usecase.auth.signin

import com.conceptic.andcourse.data.api.auth.AuthApiExecutor
import com.conceptic.andcourse.data.api.auth.model.SignInRequest
import com.conceptic.andcourse.usecase.UseCase
import io.reactivex.Observable

class SignInCase(
    private val authApiExecutor: AuthApiExecutor
) : UseCase<SignInParams, String> {
    override fun execute(param: SignInParams): Observable<String> = authApiExecutor
        .signIn(SignInRequest(email = param.email, password = param.password))
        .map { it.jwt }
}