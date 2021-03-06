package com.conceptic.andcourse.data.model

enum class ChartViewType {
    CHART_LINE,
    CHART_PIE,
    CHART_BAR;

    companion object {
        fun of(value: Int) = values().find { it.ordinal == value }
            ?: throw IllegalArgumentException("Unable to find statistics view type by y=$value")
    }
}
