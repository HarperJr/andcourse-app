package com.conceptic.andcourse.presentation.personal

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.conceptic.andcourse.data.api.ApiException
import com.conceptic.andcourse.data.api.auth.JwtTokenProvider
import com.conceptic.andcourse.data.model.Credentials
import com.conceptic.andcourse.presentation.base.BaseViewModel
import com.conceptic.andcourse.usecase.auth.credentials.CredentialsCase
import kotlinx.coroutines.*

class PersonalViewModel(
    private val credentialsCase: CredentialsCase,
    private val tokenProvider: JwtTokenProvider
) : BaseViewModel() {
    val credentialsLiveData = MutableLiveData<Credentials>()
    val logoutSuccessfulLiveDate = MutableLiveData<Unit>()
    val loadingLiveData = MutableLiveData<Boolean>()

    private val retryFetchJob = SupervisorJob()
    private val retryFetchScope = CoroutineScope(viewModelScope.coroutineContext + retryFetchJob)

    init {
        onStarted {
            if (credentialsLiveData.value == null)
                fetchCredentials()
        }
    }

    fun onLogoutBtnClicked() {
        tokenProvider.put(null)
        logoutSuccessfulLiveDate.value = Unit
    }

    fun onRetryBtnClicked() {
        retryFetchJob.cancelChildren()
        retryFetchScope.launch {
            fetchCredentials()
        }
    }

    private suspend fun fetchCredentials() {
        loadingLiveData.value = true
        runCatching {
            withContext(Dispatchers.IO) {
                credentialsCase.execute(Unit)
            }
        }.onSuccess { credentials ->
            loadingLiveData.value = false
            credentialsLiveData.value = credentials
        }.onFailure { throwable ->
            loadingLiveData.value = false
            ApiException.letFromThrowable(throwable) {
                errorMessages.value = it
            }
        }
    }
}