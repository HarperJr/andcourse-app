package com.conceptic.andcourse.presentation.statistics.adapter

import android.content.Context
import android.view.View
import com.conceptic.andcourse.data.model.Statistics
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart

class ChartViewFactory(private val context: Context) {
    fun provideBarChart(item: Statistics): View {
        return BarChartFactory.provide(context, item)
    }

    fun provideLineChart(item: Statistics): View {
        return LineChartFactory.provide(context, item)
    }

    fun providePieChart(item: Statistics): View {
        return PieChartFactory.provide(context, item)
    }

}

object BarChartFactory {
    fun provide(context: Context, item: Statistics): View {
        return BarChart(context)
    }
}

object LineChartFactory {
    fun provide(context: Context, item: Statistics): View {
        return LineChart(context)
    }
}

object PieChartFactory {
    fun provide(context: Context, item: Statistics): View {
        return PieChart(context)
    }
}