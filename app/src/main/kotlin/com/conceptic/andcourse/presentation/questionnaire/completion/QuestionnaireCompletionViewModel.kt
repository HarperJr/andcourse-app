package com.conceptic.andcourse.presentation.questionnaire.completion

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.conceptic.andcourse.data.api.ApiException
import com.conceptic.andcourse.presentation.base.BaseViewModel
import com.conceptic.andcourse.usecase.questionnaire.CompleteQuestionnaireCase
import kotlinx.coroutines.*

class QuestionnaireCompletionViewModel(
    private val completeQuestionnaireCase: CompleteQuestionnaireCase
) : BaseViewModel() {
    val completionSuccessLiveData = MutableLiveData<Unit>()

    private val completeJob = SupervisorJob()
    private val completeScope = CoroutineScope(viewModelScope.coroutineContext + completeJob)

    fun onCompleteBtnClicked() {
        completeJob.cancelChildren()
        completeScope.launch(Dispatchers.IO) {
            runCatching {
                completeQuestionnaireCase.execute(Unit)
            }.onSuccess { completionSuccessLiveData.postValue(Unit) }
                .onFailure { throwable ->
                    ApiException.letFromThrowable(throwable) {
                        errorMessages.postValue(it)
                    }
                }
        }
    }
}