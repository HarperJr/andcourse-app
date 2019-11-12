package com.conceptic.andcourse.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    val errorMessageLiveData: LiveData<String>
        get() = errorMessages

    protected val errorMessages = MutableLiveData<String>()
}