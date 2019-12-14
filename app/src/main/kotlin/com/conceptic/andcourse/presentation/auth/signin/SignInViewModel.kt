package com.conceptic.andcourse.presentation.auth.signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.conceptic.andcourse.data.api.ApiException
import com.conceptic.andcourse.presentation.base.BaseViewModel
import com.conceptic.andcourse.usecase.auth.signin.SignInCase
import com.conceptic.andcourse.usecase.auth.signin.SignInParams
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import kotlinx.coroutines.*

class SignInViewModel(
    private val signInCase: SignInCase
) : BaseViewModel() {
    val signInSuccessLiveData = MutableLiveData<Unit>()
    val loadingProgressLiveData = MutableLiveData<Boolean>()

    private val signInJob = SupervisorJob()
    private val signInScope = CoroutineScope(viewModelScope.coroutineContext + signInJob)

    fun onSignInBtnClicked(email: String, pass: String) {
        signInJob.cancelChildren()
        signInScope.launch(Dispatchers.IO) {
            loadingProgressLiveData.postValue(true)
            runCatching {
                signInCase.execute(SignInParams(email, pass))
            }.onSuccess {
                signInSuccessLiveData.postValue(it)
            }.onFailure { throwable ->
                ApiException.letFromThrowable(throwable) {
                    errorMessages.postValue(it)
                }
            }
            loadingProgressLiveData.postValue(false)
        }
    }

    fun onSignedInWithGoogle(googleAccount: GoogleSignInAccount) {
        signInJob.cancelChildren()
        signInScope.launch(Dispatchers.IO) {

        }
        signInSuccessLiveData.value = Unit
    }
}