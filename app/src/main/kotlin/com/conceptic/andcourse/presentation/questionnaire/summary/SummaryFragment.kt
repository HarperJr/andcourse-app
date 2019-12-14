package com.conceptic.andcourse.presentation.questionnaire.summary

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.conceptic.andcourse.R
import com.conceptic.andcourse.presentation.base.BaseFragment
import com.conceptic.andcourse.presentation.questionnaire.summary.adapter.SummaryAdapter
import kotlinx.android.synthetic.main.fragment_summary.*
import kotlinx.android.synthetic.main.summary_placeholder.*
import org.koin.androidx.scope.currentScope

class SummaryFragment : BaseFragment<SummaryViewModel>(R.layout.fragment_summary) {
    override val viewModel: SummaryViewModel by currentScope.inject()
    private val summaryAdapter = SummaryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.summaryLiveData.observe({ lifecycle }) { features ->
            summary_placeholder.isVisible = features.isEmpty()
            if (features.isNotEmpty()) {
                //TODO case when features are not empty
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        summary_recycler.adapter = summaryAdapter
        summary_pass_questionnaire_btn.setOnClickListener { navController.navigate(R.id.action_summaryFragment_to_introFragment) }
    }
}