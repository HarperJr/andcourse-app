package com.conceptic.andcourse.presentation.auth.signup

import com.conceptic.andcourse.data.model.Gender
import com.conceptic.andcourse.presentation.base.BaseViewModel
import com.conceptic.andcourse.usecase.auth.signup.SignUpCase
import com.conceptic.andcourse.usecase.auth.signup.SignUpParams
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SignUpViewModel(
    private val signUpCase: SignUpCase
) : BaseViewModel() {

    override fun onStart() {}

    fun onAcceptBtnClicked(email: String, dateBirth: String, password: String, repeatPassword: String, gender: Gender) {
        signUpCase
            .execute(SignUpParams(email = email, dateBirth = dateBirth, password = password, gender = gender.ordinal))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

            }, { throwable ->

            })
            .disposeWhenCleared()
    }
}
