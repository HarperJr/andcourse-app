package com.conceptic.andcourse.presentation.statistics

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import com.conceptic.andcourse.R
import com.conceptic.andcourse.presentation.MainViewModel
import com.conceptic.andcourse.presentation.base.BaseFragment
import com.conceptic.andcourse.presentation.statistics.adapter.StatisticsAdapter
import kotlinx.android.synthetic.main.fragment_statistics.*
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class StatisticsFragment : BaseFragment<StatisticsViewModel>(R.layout.fragment_statistics) {
    override val viewModel: StatisticsViewModel by currentScope.viewModel(this)
    private val mainViewModel: MainViewModel by sharedViewModel()
    private val statisticsAdapter = StatisticsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

        with(viewModel) {
            statisticsLiveData.observe({ lifecycle }) { statistics ->
                statistics_placeholder.isVisible = statistics.isEmpty()
                statisticsAdapter.items = statistics
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        statistics_view_pager.adapter = statisticsAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        val accountMenuItem = menu.findItem(R.id.item_account)

        val userRole = mainViewModel.roleLiveData.value?.second
        userRole?.let {
            accountMenuItem.setIcon(R.drawable.ic_account)
        } ?: accountMenuItem.setIcon(R.drawable.ic_signin)
    }

    /**
     * Workaround for leaving the app when you are on this screen
     */
    override fun onBackPressed(): Boolean {
        requireActivity().finish()
        return false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.item_account -> {
            onAccountItemClicked()
            true
        }
        else -> false
    }

    private fun onAccountItemClicked() {
        val userRole = mainViewModel.roleLiveData.value?.second
        userRole?.let {
            navController.navigate(R.id.action_statisticsFragment_to_personalPageFragment)
        } ?: navController.navigate(R.id.auth_navigation)
    }
}