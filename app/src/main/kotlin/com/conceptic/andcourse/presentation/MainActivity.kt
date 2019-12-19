package com.conceptic.andcourse.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.conceptic.andcourse.R
import com.conceptic.andcourse.data.model.Role
import com.conceptic.andcourse.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val viewModel: MainViewModel by viewModel()

    private val navController: NavController
        get() = findNavController(R.id.nav_host_fragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toolbar.setupWithNavController(navController)

        setSupportActionBar(toolbar)

        viewModel.roleLiveData.observe({ lifecycle }) { role ->
            role?.let {
                if (role == Role.OBSERVER)
                    navController.navigate(R.id.statisticsFragment)
            } ?: navController.navigate(R.id.auth_navigation)
            invalidateOptionsMenu()
        }
    }

    override fun onStart() {
        super.onStart()
        navController.addOnDestinationChangedListener(onDestinationChangeListener)
    }

    override fun onStop() {
        navController.removeOnDestinationChangedListener(onDestinationChangeListener)
        super.onStop()
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        if (fragment !is BaseFragment<*> || fragment.onBackPressed()) {
            if (!navController.navigateUp()) {
                super.onBackPressed()
            }
        }
    }

    private val onDestinationChangeListener =
        NavController.OnDestinationChangedListener { _, destination, _ ->
            val isToolbarVisible = when (destination.id) {
                R.id.summaryFragment,
                R.id.statisticsFragment -> true
                else -> false
            }
            toolbar.isVisible = isToolbarVisible
        }
}