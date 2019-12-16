package com.conceptic.andcourse.presentation

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.conceptic.andcourse.R
import com.conceptic.andcourse.presentation.base.BaseFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val navController: NavController
        get() = findNavController(R.id.nav_host_fragment)

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        if (fragment !is BaseFragment<*> || fragment.onBackPressed()) {
            if (!navController.navigateUp()) {
                super.onBackPressed()
            }
        }
    }
}