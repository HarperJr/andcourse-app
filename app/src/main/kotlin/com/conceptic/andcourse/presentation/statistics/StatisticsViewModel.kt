package com.conceptic.andcourse.presentation.statistics

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.conceptic.andcourse.data.api.ApiException
import com.conceptic.andcourse.data.model.Statistics
import com.conceptic.andcourse.presentation.base.BaseViewModel
import com.conceptic.andcourse.usecase.statistics.StatisticsCase
import kotlinx.coroutines.*

class StatisticsViewModel(
    private val statisticsCase: StatisticsCase
) : BaseViewModel() {
    val statisticsLiveData = MutableLiveData<List<Statistics>>()

    private val refreshFetchJob = SupervisorJob()
    private val refreshFetchScope = CoroutineScope(viewModelScope.coroutineContext + refreshFetchJob)

    init {
        onStarted {
            fetchStatistics()
        }
    }

    private suspend fun fetchStatistics() {
        runCatching {
            withContext(Dispatchers.IO) {
                statisticsCase.execute(Unit)
            }
        }.onSuccess { statistics ->
            statisticsLiveData.value = statistics
        }.onFailure { throwable ->
            ApiException.letFromThrowable(throwable) {
                errorMessages.value = it
            }
        }
    }
}
