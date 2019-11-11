package com.conceptic.andcourse.presentation.auth.signup

import androidx.lifecycle.viewModelScope
import com.conceptic.andcourse.data.api.ApiException
import com.conceptic.andcourse.data.model.Gender
import com.conceptic.andcourse.presentation.base.BaseViewModel
import com.conceptic.andcourse.usecase.auth.signup.SignUpCase
import com.conceptic.andcourse.usecase.auth.signup.SignUpParams
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val signUpCase: SignUpCase
) : BaseViewModel() {

    override fun onStart() {}

    fun onAcceptBtnClicked(
        email: String, dateBirth: String,
        password: String, gender: Gender
    ) = viewModelScope.launch(Dispatchers.IO) {
        runCatching {
            signUpCase
                .execute(SignUpParams(email = email, dateBirth = dateBirth, password = password, gender = gender))
        }.onSuccess { }
            .onFailure { throwable ->
                ApiException.letFromThrowable(throwable) { errorMessages.value = it }
            }
    }
}
