package com.conceptic.andcourse.presentation.questionnaire.intro

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.conceptic.andcourse.data.api.ApiException
import com.conceptic.andcourse.presentation.base.BaseViewModel
import com.conceptic.andcourse.usecase.questionnaire.intro.BeginQuestionnaireCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class IntroViewModel(
    private val beginQuestionnaireCase: BeginQuestionnaireCase
) : BaseViewModel() {
    val beginSuccessLiveData = MutableLiveData<Unit>()

    fun onBeginBtnClicked() = viewModelScope.launch {
        runCatching {
            withContext(Dispatchers.IO) { beginQuestionnaireCase.execute(Unit) }
        }.onSuccess { beginSuccessLiveData.postValue(Unit) }
            .onFailure { throwable ->
                ApiException.letFromThrowable(throwable) {
                    errorMessages.postValue(it)
                }
            }
    }
}