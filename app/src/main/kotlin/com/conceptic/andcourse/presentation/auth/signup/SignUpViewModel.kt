package com.conceptic.andcourse.presentation.auth.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.conceptic.andcourse.data.api.ApiException
import com.conceptic.andcourse.data.model.Gender
import com.conceptic.andcourse.presentation.base.BaseViewModel
import com.conceptic.andcourse.usecase.auth.signup.SignUpCase
import com.conceptic.andcourse.usecase.auth.signup.SignUpParams
import kotlinx.coroutines.*
import java.util.*

class SignUpViewModel(
    private val signUpCase: SignUpCase
) : BaseViewModel() {
    val signUpSuccessLiveData = MutableLiveData<Unit>()
    val loadingProgressLiveData = MutableLiveData<Boolean>()

    private val signUpJob = SupervisorJob()
    private val signUpScope = CoroutineScope(viewModelScope.coroutineContext + signUpJob)

    fun onSignUpBtnClicked(email: String, dateBirth: Date, password: String, gender: Gender) {
        signUpJob.cancelChildren()
        signUpScope.launch(Dispatchers.IO) {
            loadingProgressLiveData.postValue(true)
            loadingProgressLiveData.runCatching {
                signUpCase
                    .execute(SignUpParams(email, password, dateBirth, gender))
            }.onSuccess {
                signUpSuccessLiveData.postValue(Unit)
            }.onFailure { throwable ->
                ApiException.letFromThrowable(throwable) {
                    errorMessages.postValue(it)
                }
            }
            loadingProgressLiveData.postValue(false)
        }
    }
}
