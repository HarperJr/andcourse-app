package com.conceptic.andcourse.presentation.auth.signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.conceptic.andcourse.data.api.ApiException
import com.conceptic.andcourse.presentation.base.BaseViewModel
import com.conceptic.andcourse.usecase.auth.signin.SignInCase
import com.conceptic.andcourse.usecase.auth.signin.SignInParams
import kotlinx.coroutines.*

class SignInViewModel(
    private val signInCase: SignInCase
) : BaseViewModel() {
    val signInSuccessLiveData = MutableLiveData<Unit>()

    private val signInJob = SupervisorJob()
    private val signInScope = CoroutineScope(viewModelScope.coroutineContext + signInJob)

    fun onSignInBtnClicked(email: String, pass: String) {
        signInJob.cancelChildren()
        signInScope.launch(Dispatchers.IO) {
            runCatching {
                signInCase.execute(SignInParams(email, pass))
            }.onSuccess {
                signInSuccessLiveData.postValue(it)
            }.onFailure { throwable ->
                ApiException.letFromThrowable(throwable) {
                    errorMessages.postValue(it)
                }
            }
        }
    }
}