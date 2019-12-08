package com.conceptic.andcourse.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.conceptic.andcourse.R
import com.conceptic.andcourse.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        val fragment = currentFragment
        if (fragment !is BaseFragment<*> || fragment.onBackPressed()) {
            if (!findNavController(R.id.nav_host_fragment).navigateUp()) {
                super.onBackPressed()
            }
        }
    }

    private val currentFragment: Fragment?
        get() = nav_host_fragment.childFragmentManager.findFragmentById(R.id.nav_host_fragment)
}