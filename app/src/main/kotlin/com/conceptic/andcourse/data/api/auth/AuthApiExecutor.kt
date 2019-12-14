package com.conceptic.andcourse.data.api.auth

import android.content.Context
import com.conceptic.andcourse.data.api.ApiExecutor
import com.conceptic.andcourse.data.api.auth.model.CredentialsResponse
import com.conceptic.andcourse.data.api.auth.model.JwtTokenResponse
import com.conceptic.andcourse.data.api.auth.model.SignInRequest
import com.conceptic.andcourse.data.api.auth.model.SignUpRequest

/**
 * ApiExecutor's interface
 */
interface AuthApiExecutor {
    suspend fun signUp(request: SignUpRequest)

    suspend fun signIn(request: SignInRequest): JwtTokenResponse

    suspend fun credentials(): CredentialsResponse
}

class AuthApiExecutorImpl(context: Context, authApi: AuthApi) : ApiExecutor<AuthApi>(context, authApi),
    AuthApiExecutor {
    override suspend fun signUp(request: SignUpRequest) = executeService { signUpAsync(request) }

    override suspend fun signIn(request: SignInRequest) = executeService { signInAsync(request) }

    override suspend fun credentials(): CredentialsResponse = executeService { credentialsAsync() }
}
