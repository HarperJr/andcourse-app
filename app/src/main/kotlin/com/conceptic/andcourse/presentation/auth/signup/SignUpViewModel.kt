package com.conceptic.andcourse.presentation.auth.signup

import com.conceptic.andcourse.presentation.base.BaseViewModel
import com.conceptic.andcourse.usecase.auth.signup.SignUpCase

class SignUpViewModel(
    private val signUpCase: SignUpCase
) : BaseViewModel() {
    override fun onStart() {}
}
