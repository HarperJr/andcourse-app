package com.conceptic.andcourse.usecase.auth.signin

import com.conceptic.andcourse.data.api.auth.AuthApiExecutor
import com.conceptic.andcourse.data.api.auth.JwtTokenProvider
import com.conceptic.andcourse.data.api.auth.model.SignInRequest
import com.conceptic.andcourse.usecase.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

class SignInCase(
    private val authApiExecutor: AuthApiExecutor,
    private val jwtTokenProvider: JwtTokenProvider
) : UseCase<SignInParams, Unit> {
    override suspend fun execute(param: SignInParams): Unit = coroutineScope {
        val response = authApiExecutor
            .signIn(SignInRequest(email = param.email, password = param.password))
        withContext(Dispatchers.Main) {
            jwtTokenProvider.put(jwt = response.jwt)
        }
    }
}