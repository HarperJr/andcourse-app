package com.conceptic.andcourse.data.model

typealias Dot = Pair<Int, Int>

data class Statistics(
    val statisticsViewType: StatisticsViewType,
    val name: String,
    val dots: List<Dot>
)
