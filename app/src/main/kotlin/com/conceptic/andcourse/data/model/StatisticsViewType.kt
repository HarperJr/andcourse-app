package com.conceptic.andcourse.data.model

enum class StatisticsViewType {
    CHART_LINE,
    CHART_PIE,
    CHART_COLUMNS;

    companion object {
        fun of(value: Int) = values().find { it.ordinal == value }
            ?: throw IllegalArgumentException("Unable to find statistics view type by value=$value")
    }
}
