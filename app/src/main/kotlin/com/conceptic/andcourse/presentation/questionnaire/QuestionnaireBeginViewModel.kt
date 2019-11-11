package com.conceptic.andcourse.presentation.questionnaire

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.conceptic.andcourse.data.api.ApiException
import com.conceptic.andcourse.presentation.base.BaseViewModel
import com.conceptic.andcourse.usecase.questionnaire.BeginQuestionnaireCase
import kotlinx.coroutines.launch

class QuestionnaireBeginViewModel(
    private val beginQuestionnaireCase: BeginQuestionnaireCase
) : BaseViewModel() {
    val beginSuccessLiveData = MutableLiveData<Unit>()

    fun onBeginBtnClicked() {
        viewModelScope.launch {
            runCatching {
                beginQuestionnaireCase.execute(Unit)
            }.onSuccess { beginSuccessLiveData.value = Unit }
                .onFailure { throwable ->
                    ApiException.letFromThrowable(throwable) {
                        errorMessages.postValue(it)
                    }
                }
        }
    }
}