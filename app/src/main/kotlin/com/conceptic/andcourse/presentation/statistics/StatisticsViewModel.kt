package com.conceptic.andcourse.presentation.statistics

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.conceptic.andcourse.data.model.Statistics
import com.conceptic.andcourse.presentation.base.BaseViewModel
import com.conceptic.andcourse.usecase.statistics.StatisticsCase
import kotlinx.coroutines.*

class StatisticsViewModel(
    private val statisticsCase: StatisticsCase
) : BaseViewModel() {
    val statisticsLiveData = MutableLiveData<List<Statistics>>()

    private val retryFetchJob = SupervisorJob()
    private val retryFetchScope = CoroutineScope(viewModelScope.coroutineContext + retryFetchJob)

    init {
        onStarted {
            fetchStatistics()
        }
    }

    private suspend fun fetchStatistics() {
        val statistics = withContext(Dispatchers.IO) {
            statisticsCase.execute(Unit)
        }
        statisticsLiveData.value = statistics
    }

    fun onRefreshed() {
        retryFetchJob.cancelChildren()
        retryFetchScope.launch {
            fetchStatistics()
        }
    }
}
