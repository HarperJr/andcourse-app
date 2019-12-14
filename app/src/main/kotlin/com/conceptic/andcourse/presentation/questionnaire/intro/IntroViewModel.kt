package com.conceptic.andcourse.presentation.questionnaire.intro

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.conceptic.andcourse.data.api.ApiException
import com.conceptic.andcourse.presentation.base.BaseViewModel
import com.conceptic.andcourse.usecase.questionnaire.intro.BeginQuestionnaireCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IntroViewModel(
    private val beginQuestionnaireCase: BeginQuestionnaireCase
) : BaseViewModel() {
    val loadingLiveData = MutableLiveData<Boolean>()
    val beginSuccessLiveData = MutableLiveData<Unit>()

    fun onBeginBtnClicked() = viewModelScope.launch(Dispatchers.IO) {
        runCatching {
            loadingLiveData.postValue(true)
            beginQuestionnaireCase.execute(Unit)
        }.onSuccess {
            loadingLiveData.postValue(false)
            beginSuccessLiveData.postValue(Unit)
        }.onFailure { throwable ->
            loadingLiveData.postValue(false)
            ApiException.letFromThrowable(throwable) {
                errorMessages.postValue(it)
            }
        }
    }
}