package com.conceptic.andcourse.presentation

import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.conceptic.andcourse.data.api.auth.JwtTokenProvider
import com.conceptic.andcourse.data.model.Role
import com.conceptic.andcourse.presentation.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

class MainViewModel(
    private val jwtTokenProvider: JwtTokenProvider
) : BaseViewModel() {
    @ExperimentalCoroutinesApi
    val roleLiveData = liveData(viewModelScope.coroutineContext) {
        jwtTokenProvider.observe { jwtToken ->
            val role = jwtToken?.let { jwt ->
                if (!jwt.expired()) {
                    jwt.getClaim(ROLE_CLAIM)?.let { Role.of(it.asInt()!!) }
                } else null
            }
            emit(isFirstReq to role)
            isFirstReq = false
        }
    }
    private var isFirstReq = true

    companion object {
        private const val ROLE_CLAIM = "role"
    }
}