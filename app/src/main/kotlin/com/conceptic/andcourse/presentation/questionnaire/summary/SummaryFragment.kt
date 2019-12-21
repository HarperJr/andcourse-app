package com.conceptic.andcourse.presentation.questionnaire.summary

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import com.conceptic.andcourse.R
import com.conceptic.andcourse.presentation.MainViewModel
import com.conceptic.andcourse.presentation.base.BaseFragment
import com.conceptic.andcourse.presentation.questionnaire.summary.adapter.SummaryAdapter
import com.conceptic.andcourse.presentation.questionnaire.summary.view.OffsetDecoration
import com.conceptic.andcourse.presentation.view.LoadingProgressDialog
import kotlinx.android.synthetic.main.fragment_summary.*
import kotlinx.android.synthetic.main.summary_placeholder.*
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SummaryFragment : BaseFragment<SummaryViewModel>(R.layout.fragment_summary) {
    override val viewModel: SummaryViewModel by currentScope.inject()
    private val mainViewModel: MainViewModel by sharedViewModel()

    private val summaryAdapter = SummaryAdapter {
        navController.navigate(R.id.action_summaryFragment_to_introFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(viewModel) {
            loadingLiveData.observe({ lifecycle }) { setLoadingVisible(it) }
            summaryLiveData.observe({ lifecycle }) { features ->
                summary_placeholder.isVisible = features.isEmpty()
                if (features.isNotEmpty()) {
                    summaryAdapter.items = features
                }
            }
        }

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        val accountMenuItem = menu.findItem(R.id.item_account)

        val userRole = mainViewModel.roleLiveData.value
        userRole?.let {
            accountMenuItem.setIcon(R.drawable.ic_account)
        } ?: accountMenuItem.setIcon(R.drawable.ic_signin)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.item_account -> {
            onAccountItemClicked()
            true
        }
        else -> false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        summary_recycler.apply {
            addItemDecoration(OffsetDecoration(ITEMS_OFFSET, ITEMS_OFFSET))
            adapter = summaryAdapter
        }
        no_statistics_refresh_btn.setOnClickListener { navController.navigate(R.id.action_summaryFragment_to_introFragment) }
    }

    override fun handleError(message: String) {
        summary_placeholder.isVisible = true
    }

    private fun setLoadingVisible(visible: Boolean) = LoadingProgressDialog.setVisible(this, visible)

    private fun onAccountItemClicked() {
        val userRole = mainViewModel.roleLiveData.value
        userRole?.let {
            navController.navigate(R.id.action_summaryFragment_to_personalPageFragment)
        } ?: navController.navigate(R.id.auth_navigation)
    }

    companion object {
        private const val ITEMS_OFFSET = 10
    }
}