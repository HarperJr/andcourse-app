package com.conceptic.andcourse.usecase.auth.signup

import com.conceptic.andcourse.data.api.auth.AuthApiExecutor
import com.conceptic.andcourse.data.api.auth.model.SignUpRequest
import com.conceptic.andcourse.usecase.UseCase
import kotlinx.coroutines.coroutineScope

class SignUpCase(
    private val authApiExecutor: AuthApiExecutor
) : UseCase<SignUpParams, Unit> {
    override suspend fun execute(param: SignUpParams) = coroutineScope {
        authApiExecutor.signUp(
            SignUpRequest(
                email = param.email,
                password = param.password,
                dateBirth = param.dateBirth.time,
                gender = param.gender.ordinal
            )
        )
    }
}