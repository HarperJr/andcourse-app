package com.conceptic.andcourse.presentation.questionnaire.summary

import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.conceptic.andcourse.data.api.ApiException
import com.conceptic.andcourse.data.model.Feature
import com.conceptic.andcourse.presentation.base.BaseViewModel
import com.conceptic.andcourse.usecase.questionnaire.summary.SummaryCase
import kotlinx.coroutines.coroutineScope

class SummaryViewModel(
    summaryCase: SummaryCase
) : BaseViewModel() {
    val summaryLiveData = liveData<List<Feature>>(viewModelScope.coroutineContext) {
        coroutineScope {
            runCatching {
                summaryCase.execute(Unit)
            }.onSuccess { features -> emit(features) }
                .onFailure { throwable ->
                    ApiException.letFromThrowable(throwable) { message ->
                        errorMessages.value = message
                    }
                }
        }
    }
}