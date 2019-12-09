package com.conceptic.andcourse.presentation.questionnaire.summary

import com.conceptic.andcourse.R
import com.conceptic.andcourse.presentation.base.BaseFragment
import org.koin.androidx.scope.currentScope

class SummaryFragment : BaseFragment<SummaryViewModel>(R.layout.fragment_summary) {
    override val viewModel: SummaryViewModel by currentScope.inject()
}