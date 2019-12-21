package com.conceptic.andcourse.data.model

data class Statistics(
    val chartViewType: ChartViewType,
    val name: String,
    val data: ChartData
) {
    data class ChartData(val points: List<Int>, val values: List<Int>)
}
