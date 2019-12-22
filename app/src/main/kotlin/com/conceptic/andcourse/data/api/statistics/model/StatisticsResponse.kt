package com.conceptic.andcourse.data.api.statistics.model

data class StatisticsResponse(val date: Long, val statistics: List<Statistics>) {
    data class Statistics(val chartViewType: Int, val xAxisLabel: String, val yAxisLabel: String, val title: String, val data: List<ChartValue>) {
        data class ChartValue(val x: Float, val y: Float, val label: String)
    }
}
