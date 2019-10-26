package com.conceptic.andcourse.data.api.auth

import com.conceptic.andcourse.data.api.ApiExecutor
import com.conceptic.andcourse.data.api.auth.model.JwtTokenResponse
import com.conceptic.andcourse.data.api.auth.model.SignInRequest
import com.conceptic.andcourse.data.api.auth.model.SingUpRequest
import io.reactivex.Observable

/**
 * ApiExecutor's interface
 */
interface AuthApiExecutor {
    fun signUp(request: SingUpRequest): Observable<Nothing>

    fun signIn(request: SignInRequest): Observable<JwtTokenResponse>
}

class AuthApiExecutorImpl(authApi: AuthApi) : ApiExecutor<AuthApi>(authApi), AuthApiExecutor {
    override fun signUp(request: SingUpRequest): Observable<Nothing> = executeService { signUp(request) }

    override fun signIn(request: SignInRequest): Observable<JwtTokenResponse> = executeService { signIn(request) }
}
