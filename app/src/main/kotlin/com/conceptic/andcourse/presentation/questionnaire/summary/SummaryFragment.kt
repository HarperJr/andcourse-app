package com.conceptic.andcourse.presentation.questionnaire.summary

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.conceptic.andcourse.R
import com.conceptic.andcourse.presentation.MainViewModel
import com.conceptic.andcourse.presentation.base.BaseFragment
import com.conceptic.andcourse.presentation.questionnaire.summary.adapter.SummaryAdapter
import com.conceptic.andcourse.presentation.view.LoadingProgressDialog
import kotlinx.android.synthetic.main.fragment_summary.*
import kotlinx.android.synthetic.main.summary_placeholder.*
import org.koin.androidx.scope.currentScope

class SummaryFragment : BaseFragment<SummaryViewModel>(R.layout.fragment_summary) {
    override val viewModel: SummaryViewModel by currentScope.inject()
    private val summaryAdapter = SummaryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedViewModel = requireActivity().currentScope.get<MainViewModel>()
        sharedViewModel.credentialsLiveData.observe({ lifecycle }) { credentials ->
            if (credentials == null) {
                navController.navigate(R.id.action_summaryFragment_to_auth_navigation)
            } else viewModel.onCredentialsReceived(credentials)
        }
        with(viewModel) {
            loadingLiveData.observe({ lifecycle }) { setLoadingVisible(it) }
            statisticsRoutingLiveData.observe({ lifecycle }) { navController.navigate(R.id.action_summaryFragment_to_statisticsFragment) }
            summaryLiveData.observe({ lifecycle }) { features ->
                summary_placeholder.isVisible = features.isEmpty()
                if (features.isNotEmpty()) {
                    summaryAdapter.items = features
                }
            }
        }
    }

    override fun handleError(message: String) {
        summary_placeholder.isVisible = true
    }

    private fun setLoadingVisible(visible: Boolean) = LoadingProgressDialog.setVisible(this, visible)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        summary_recycler.adapter = summaryAdapter
        summary_pass_questionnaire_btn.setOnClickListener { navController.navigate(R.id.action_summaryFragment_to_introFragment) }
    }
}