package com.conceptic.andcourse.usecase.auth.signup

import com.conceptic.andcourse.data.api.auth.AuthApiExecutor
import com.conceptic.andcourse.data.api.auth.model.SignUpRequest
import com.conceptic.andcourse.usecase.UseCase
import io.reactivex.Observable

class SignUpCase(
    private val authApiExecutor: AuthApiExecutor
) : UseCase<SignUpParams, Nothing> {
    override fun execute(param: SignUpParams): Observable<Nothing> {
        return authApiExecutor.signUp(
            SignUpRequest(
                email = param.email,
                password = param.password,
                dateBirth = param.dateBirth,
                gender = param.gender.ordinal
            )
        )
    }
}