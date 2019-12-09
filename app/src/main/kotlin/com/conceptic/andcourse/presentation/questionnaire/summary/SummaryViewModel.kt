package com.conceptic.andcourse.presentation.questionnaire.summary

import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.conceptic.andcourse.data.model.Feature
import com.conceptic.andcourse.presentation.base.BaseViewModel
import com.conceptic.andcourse.usecase.questionnaire.summary.SummaryCase

class SummaryViewModel(
    summaryCase: SummaryCase
) : BaseViewModel() {
    val summaryLiveData = liveData<List<Feature>>(viewModelScope.coroutineContext) { summaryCase.execute(Unit) }
}