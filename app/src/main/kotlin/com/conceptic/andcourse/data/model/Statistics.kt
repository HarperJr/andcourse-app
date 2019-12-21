package com.conceptic.andcourse.data.model

data class Statistics(
    val chartViewType: ChartViewType,
    val data: ChartData
) {
    data class ChartData(val title: String, val values: List<ChartValue>)
}

data class ChartValue(val x: Float, val y: Float, val label: String)
