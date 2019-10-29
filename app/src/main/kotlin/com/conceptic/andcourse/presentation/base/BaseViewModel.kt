package com.conceptic.andcourse.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {
    val errorMessageLiveData: LiveData<String>
        get() = errorMessages

    protected val errorMessages = MutableLiveData<String>()

    private val compositeDisposable = CompositeDisposable()

    abstract fun onStart()

    protected fun Disposable.disposeWhenCleared() = compositeDisposable.add(this)

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}