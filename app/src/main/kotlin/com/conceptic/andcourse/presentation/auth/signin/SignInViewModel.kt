package com.conceptic.andcourse.presentation.auth.signin

import androidx.lifecycle.MutableLiveData
import com.conceptic.andcourse.data.api.ApiException
import com.conceptic.andcourse.presentation.base.BaseViewModel
import com.conceptic.andcourse.usecase.auth.signin.SignInCase
import com.conceptic.andcourse.usecase.auth.signin.SignInParams
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers

class SignInViewModel(
    private val signInCase: SignInCase
) : BaseViewModel() {
    val signInSuccessLiveData = MutableLiveData<Unit>()
    private var signInDisposable = Disposables.disposed()

    override fun onStart() {}

    fun onAcceptBtnClicked(email: String, pass: String) {
        signInDisposable.dispose()
        signInDisposable = signInCase.execute(SignInParams(email, pass))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                signInSuccessLiveData.value = Unit
            }, { throwable ->
                ApiException.letFromThrowable(throwable) {
                    errorMessages.postValue(it)
                }
            })
    }
}