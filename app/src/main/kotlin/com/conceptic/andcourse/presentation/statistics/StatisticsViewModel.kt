package com.conceptic.andcourse.presentation.statistics

import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.conceptic.andcourse.data.model.Statistics
import com.conceptic.andcourse.presentation.base.BaseViewModel
import com.conceptic.andcourse.usecase.statistics.StatisticsCase

class StatisticsViewModel(
    private val statisticsCase: StatisticsCase
) : BaseViewModel() {
    val statisticsLiveData = liveData<Statistics>(viewModelScope.coroutineContext) {

    }
}
