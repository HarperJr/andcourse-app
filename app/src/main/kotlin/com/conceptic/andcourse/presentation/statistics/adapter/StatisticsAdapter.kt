package com.conceptic.andcourse.presentation.statistics.adapter

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.conceptic.andcourse.R
import com.conceptic.andcourse.data.model.Statistics
import com.conceptic.andcourse.support.AndroidUtils
import kotlinx.android.synthetic.main.item_statistics_page.view.*

class StatisticsAdapter : RecyclerView.Adapter<StatisticsAdapter.ViewHolder>() {
    var items: List<Statistics> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_statistics_page, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val chartViewFactory = ChartViewFactory(itemView.context)
        private var chartIsPresent = false

        fun bind(item: Statistics) {
            with(itemView) {
                if (!chartIsPresent) {
                    chartIsPresent = true
                    val chartView = chartViewFactory.provideChart(item)
                    val layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT, Gravity.CENTER)
                        .apply {
                            val margin = AndroidUtils.dpToPx(CHART_MARGIN_DP)
                            bottomMargin = margin
                            topMargin = margin
                            leftMargin = margin
                            rightMargin = margin
                        }
                    chart_container.addView(
                        chartView,
                        layoutParams
                    )
                }
            }
        }
    }

    companion object {
        private const val CHART_MARGIN_DP = 8
    }
}
