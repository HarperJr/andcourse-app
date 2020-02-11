package com.conceptic.andcourse.presentation.questionnaire.summary

import androidx.lifecycle.MutableLiveData
import com.conceptic.andcourse.data.api.ApiException
import com.conceptic.andcourse.data.model.Feature
import com.conceptic.andcourse.presentation.base.BaseViewModel
import com.conceptic.andcourse.usecase.questionnaire.summary.SummaryCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SummaryViewModel(
    private val summaryCase: SummaryCase
) : BaseViewModel() {
    val loadingLiveData = MutableLiveData<Boolean>()
    val summaryLiveData = MutableLiveData<List<Feature>>()

    init {
        onStarted {
            fetchSummary()
        }
    }

    private suspend fun fetchSummary() {
        runCatching {
            loadingLiveData.value = true
            withContext(Dispatchers.IO) {
                summaryCase.execute(Unit)
            }
        }.onSuccess { summaryFeatures ->
            loadingLiveData.value = false
            summaryLiveData.value = summaryFeatures
        }.onFailure { throwable ->
            loadingLiveData.value = false
            ApiException.letFromThrowable(throwable) {
                errorMessages.postValue(it)
            }
        }
    }
}