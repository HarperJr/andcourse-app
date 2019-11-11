package com.conceptic.andcourse.presentation.auth.signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.conceptic.andcourse.data.api.ApiException
import com.conceptic.andcourse.presentation.base.BaseViewModel
import com.conceptic.andcourse.usecase.auth.signin.SignInCase
import com.conceptic.andcourse.usecase.auth.signin.SignInParams
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignInViewModel(
    private val signInCase: SignInCase
) : BaseViewModel() {
    val signInSuccessLiveData = MutableLiveData<Unit>()

    override fun onStart() {}

    fun onSignInBtnClicked(email: String, pass: String) = viewModelScope.launch(Dispatchers.IO) {
        runCatching {
            signInCase.execute(SignInParams(email, pass))
        }.onSuccess { signInSuccessLiveData.value = it }
            .onFailure { throwable ->
                ApiException.letFromThrowable(throwable) {
                    errorMessages.postValue(it)
                }
            }
    }
}