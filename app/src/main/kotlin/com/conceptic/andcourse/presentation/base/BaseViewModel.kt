package com.conceptic.andcourse.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent

interface OnStartedDelegate {
    suspend fun onStarted()
}

abstract class BaseViewModel : ViewModel(), KoinComponent {
    val errorMessageLiveData: LiveData<String>
        get() = errorMessages

    protected val errorMessages = MutableLiveData<String>()
    private var onStartedInvocation: OnStartedDelegate? = null

    fun onStart() = viewModelScope.launch {
        onStartedInvocation?.onStarted()
    }

    override fun onCleared() {
        onStartedInvocation = null
    }

    protected fun onStarted(invocation: suspend () -> Unit) {
        onStartedInvocation = object : OnStartedDelegate {
            override suspend fun onStarted() {
                invocation.invoke()
            }
        }
    }
}