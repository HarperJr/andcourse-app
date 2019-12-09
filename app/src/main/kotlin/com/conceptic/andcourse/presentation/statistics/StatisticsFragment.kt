package com.conceptic.andcourse.presentation.statistics

import com.conceptic.andcourse.R
import com.conceptic.andcourse.presentation.base.BaseFragment
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel

class StatisticsFragment : BaseFragment<StatisticsViewModel>(R.layout.fragment_statistics) {
    override val viewModel: StatisticsViewModel by currentScope.viewModel(this)


}