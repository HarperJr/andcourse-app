package com.conceptic.andcourse.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.conceptic.andcourse.data.model.Credentials
import com.conceptic.andcourse.usecase.auth.credentials.CredentialsCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainViewModel(
    private val credentialsCase: CredentialsCase
) : ViewModel() {
    val credentialsLiveData = liveData<Credentials?>(viewModelScope.coroutineContext) {
        runCatching {
            withContext(Dispatchers.IO) {
                credentialsCase.execute(Unit)
            }
        }.onSuccess { credentials -> emit(credentials) }
            .onFailure { emit(null) }
    }
}