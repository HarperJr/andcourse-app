package com.conceptic.andcourse.presentation

import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.conceptic.andcourse.data.api.auth.JwtTokenProvider
import com.conceptic.andcourse.data.model.Role
import com.conceptic.andcourse.presentation.base.BaseViewModel

class MainViewModel(
    private val jwtTokenProvider: JwtTokenProvider
) : BaseViewModel() {

    val roleLiveData = liveData(viewModelScope.coroutineContext) {
        jwtTokenProvider.observe { jwtToken ->
            val role = jwtToken?.let { jwt ->
                if (!jwt.expired()) {
                    jwt.getClaim(ROLE_CLAIM)?.let { Role.of(it.asInt()!!) }
                } else null
            }
            emit(role)
        }
    }

    companion object {
        private const val ROLE_CLAIM = "role"
    }
}