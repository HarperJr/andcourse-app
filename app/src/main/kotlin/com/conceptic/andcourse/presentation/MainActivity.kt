package com.conceptic.andcourse.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
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
        setSupportActionBar(toolbar)

        viewModel.roleLiveData.observe({ lifecycle }) { role ->
            role?.let {
                if (role == Role.OBSERVER)
                    navController.navigate(R.id.statisticsFragment, null)
            } ?: navController.navigate(R.id.auth_navigation)
            invalidateOptionsMenu()
        }
    }

    override fun onBackPressed() {
        val currentFragment = nav_host_fragment.childFragmentManager
            .findFragmentById(R.id.nav_host_fragment)
        if (currentFragment !is BaseFragment<*> || currentFragment.onBackPressed()) {
            if (!navController.navigateUp()) {
                super.onBackPressed()
            }
        }
    }
}