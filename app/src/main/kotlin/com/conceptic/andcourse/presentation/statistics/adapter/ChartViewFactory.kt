package com.conceptic.andcourse.presentation.statistics.adapter

import android.content.Context
import android.graphics.Color
import android.view.View
import com.conceptic.andcourse.data.model.ChartViewType
import com.conceptic.andcourse.data.model.Statistics
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.Chart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import java.text.DecimalFormat

class ChartViewFactory(private val context: Context) {
    fun provideChart(item: Statistics): View {
        val chart = when (item.chartViewType) {
            ChartViewType.CHART_BAR -> provideBarChart(item)
            ChartViewType.CHART_LINE -> provideLineChart(item)
            ChartViewType.CHART_PIE -> providePieChart(item)
        }

        return chart.apply {
            with(legend) {
                verticalAlignment = Legend.LegendVerticalAlignment.TOP
                horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
                setDrawInside(false)
                xEntrySpace = 7f
                yEntrySpace = 0f
                yOffset = 0f
            }
            setTouchEnabled(false)
            invalidate()
        }
    }

    private fun provideBarChart(item: Statistics): Chart<*> {
        return BarChartFactory.provide(context, item)
    }

    private fun provideLineChart(item: Statistics): Chart<*> {
        return LineChartFactory.provide(context, item)
    }

    private fun providePieChart(item: Statistics): Chart<*> {
        return PieChartFactory.provide(context, item)
    }

    companion object {
        const val DEFAULT_VALUE_TEXT_SIZE = 12f
        val DEFAULT_AXIS_VALUE_FORMATTER: (Statistics, Boolean) -> ValueFormatter = { item, useDefaultFormat ->
            val decimalFormat = DecimalFormat("#.#")
            object : ValueFormatter() {
                override fun getAxisLabel(value: Float, axis: AxisBase): String {
                    return item.data.values.getOrNull(value.toInt())?.label
                        ?: if (useDefaultFormat) decimalFormat.format(value) else ""
                }
            }
        }
    }

    object BarChartFactory {
        private val exposedColor = Color.rgb(255, 102, 0)

        fun provide(context: Context, item: Statistics): Chart<*> {
            val data = item.data
            val entries = data.values.map { BarEntry(it.x, it.y, it.label) }
            val barDataSet = BarDataSet(entries, data.title)
            with(barDataSet) {
                setColors(exposedColor)
            }
            return BarChart(context).apply {
                val barData = BarData(barDataSet)
                applyDefaultParams(this, item)
                barData.setValueTextSize(DEFAULT_VALUE_TEXT_SIZE)
                xAxis.granularity = 1f
                setData(barData)
            }
        }

        private fun applyDefaultParams(chart: BarChart, item: Statistics) {
            with(chart.xAxis) {
                granularity = 1f
                isGranularityEnabled = true
                valueFormatter = DEFAULT_AXIS_VALUE_FORMATTER.invoke(item, false)
            }
        }
    }

    object LineChartFactory {
        private val exposedColor = Color.rgb(255, 102, 0)

        fun provide(context: Context, item: Statistics): Chart<*> {
            val data = item.data
            val entries = data.values.map { Entry(it.x, it.y, it.label) }
            val lineDataSet = LineDataSet(entries, data.title)
            with(lineDataSet) {
                lineWidth = 3f
                setColors(exposedColor)
                setCircleColors(exposedColor)
                setDrawCircleHole(false)
            }
            return LineChart(context).apply {
                applyDefaultParams(this, item)
                val lineData = LineData(lineDataSet)
                lineData.setValueTextSize(DEFAULT_VALUE_TEXT_SIZE)
                setData(lineData)
            }
        }

        private fun applyDefaultParams(chart: LineChart, item: Statistics) {
            with(chart.xAxis) {
                granularity = 1f
                isGranularityEnabled = true
                valueFormatter = DEFAULT_AXIS_VALUE_FORMATTER.invoke(item, false)
            }
        }
    }

    object PieChartFactory {
        fun provide(context: Context, item: Statistics): Chart<*> {
            val data = item.data
            val entries = data.values.map { PieEntry(it.y, it.label) }
            val pieDataSet = PieDataSet(entries, data.title)
            pieDataSet.colors = ColorTemplate.COLORFUL_COLORS.asList()
            return PieChart(context).apply {
                applyDefaultParams(this)
                val pieData = PieData(pieDataSet)
                pieData.setValueTextSize(DEFAULT_VALUE_TEXT_SIZE)
                pieData.setValueFormatter(PercentFormatter(this))
                setData(pieData)
            }
        }

        private fun applyDefaultParams(chart: PieChart) = with(chart) {
            isDrawHoleEnabled = true
            holeRadius = 58f
            transparentCircleRadius = 61f
            isHighlightPerTapEnabled = true
            setDrawCenterText(true)
        }
    }
}