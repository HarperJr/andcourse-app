package com.conceptic.andcourse.presentation

import androidx.lifecycle.LiveData
import com.conceptic.andcourse.data.api.auth.JwtTokenProvider
import com.conceptic.andcourse.data.model.Role
import com.conceptic.andcourse.presentation.base.BaseViewModel

class MainViewModel(
    private val jwtTokenProvider: JwtTokenProvider
) : BaseViewModel() {
    val roleLiveData = object : LiveData<Role>() {
        private var role: Role? = null

        override fun onActive() {
            val jwtToken = jwtTokenProvider.get()
            role = jwtToken?.let { jwt ->
                if (!jwt.expired()) {
                    jwt.getClaim(ROLE_CLAIM)?.let { Role.of(it.asInt()!!) }
                } else null
            }
            value = role
        }
    }

    companion object {
        private const val ROLE_CLAIM = "role"
    }
}