package com.conceptic.andcourse.usecase.auth.signin

import com.conceptic.andcourse.data.api.ApiExecutorFactory
import com.conceptic.andcourse.data.api.auth.JwtTokenProvider
import com.conceptic.andcourse.data.api.auth.model.SignInRequest
import com.conceptic.andcourse.usecase.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

class SignInCase(
    apiExecutorFactory: ApiExecutorFactory,
    private val jwtTokenProvider: JwtTokenProvider
) : UseCase<SignInParams, Unit> {
    private val authApiExecutor = apiExecutorFactory.authExecutor()

    override suspend fun execute(param: SignInParams): Unit = coroutineScope {
        val response = authApiExecutor
            .signIn(SignInRequest(email = param.email, password = param.password))
        withContext(Dispatchers.Main) {
            jwtTokenProvider.put(jwt = response.jwt)
        }
    }
}