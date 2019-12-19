package com.conceptic.andcourse.presentation.statistics

import android.os.Bundle
import android.view.View
import com.conceptic.andcourse.R
import com.conceptic.andcourse.presentation.base.BaseFragment
import com.conceptic.andcourse.presentation.statistics.adapter.StatisticsAdapter
import kotlinx.android.synthetic.main.fragment_statistics.*
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel

class StatisticsFragment : BaseFragment<StatisticsViewModel>(R.layout.fragment_statistics) {
    override val viewModel: StatisticsViewModel by currentScope.viewModel(this)
    private val statisticsAdapter = StatisticsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        statistics_view_pager.adapter = statisticsAdapter
    }
}