package com.conceptic.andcourse.presentation.questionnaire.summary

import android.os.Bundle
import android.view.View
import com.conceptic.andcourse.R
import com.conceptic.andcourse.presentation.base.BaseFragment
import com.conceptic.andcourse.presentation.questionnaire.summary.adapter.SummaryAdapter
import kotlinx.android.synthetic.main.fragment_summary.*
import org.koin.androidx.scope.currentScope

class SummaryFragment : BaseFragment<SummaryViewModel>(R.layout.fragment_summary) {
    override val viewModel: SummaryViewModel by currentScope.inject()
    private val summaryAdapter = SummaryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.summaryLiveData.observe({ lifecycle }) { features ->
            summaryAdapter.items = features
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        summary_recycler.adapter = summaryAdapter
    }
}