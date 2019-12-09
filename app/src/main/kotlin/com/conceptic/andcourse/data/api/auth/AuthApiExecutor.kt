package com.conceptic.andcourse.data.api.auth

import com.conceptic.andcourse.data.api.ApiExecutor
import com.conceptic.andcourse.data.api.auth.model.JwtTokenResponse
import com.conceptic.andcourse.data.api.auth.model.SignInRequest
import com.conceptic.andcourse.data.api.auth.model.SignUpRequest
import com.conceptic.andcourse.data.api.support.ConnectivityHandler

/**
 * ApiExecutor's interface
 */
interface AuthApiExecutor {
    suspend fun signUp(request: SignUpRequest)

    suspend fun signIn(request: SignInRequest): JwtTokenResponse
}

class AuthApiExecutorImpl(connectivityHandler: ConnectivityHandler, authApi: AuthApi)
    : ApiExecutor<AuthApi>(connectivityHandler, authApi), AuthApiExecutor {
    override suspend fun signUp(request: SignUpRequest) = executeService { signUpAsync(request) }

    override suspend fun signIn(request: SignInRequest) = executeService { signInAsync(request) }
}
