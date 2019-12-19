package com.conceptic.andcourse.presentation.statistics.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.conceptic.andcourse.R
import com.conceptic.andcourse.data.model.Statistics
import com.conceptic.andcourse.data.model.StatisticsViewType
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

        fun bind(item: Statistics) {
            with(itemView) {
                val chartView = when (item.statisticsViewType) {
                    StatisticsViewType.CHART_BAR -> chartViewFactory.provideBarChart(item)
                    StatisticsViewType.CHART_LINE -> chartViewFactory.provideLineChart(item)
                    StatisticsViewType.CHART_PIE -> chartViewFactory.providePieChart(item)
                }

                chart_container.addView(chartView)
            }
        }
    }
}
