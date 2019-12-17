package com.conceptic.andcourse.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.conceptic.andcourse.R
import com.conceptic.andcourse.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.scope.currentScope

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val viewModel: MainViewModel by currentScope.inject()
    private var onAccountOptionsItemClickListener: (() -> Unit)? = null

    private val navController: NavController
        get() = findNavController(R.id.nav_host_fragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toolbar.setupWithNavController(navController)

        setSupportActionBar(toolbar)

        viewModel.credentialsLiveData.observe({ lifecycle }) { credentials ->
            resolveActionBar()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.item_account -> {
            onAccountOptionsItemClickListener?.invoke()
            true
        }
        else -> false
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

    private fun resolveActionBar() {
    }

    private val onDestinationChangeListener =
        NavController.OnDestinationChangedListener { _, destination, _ ->
            val isToolbarVisible = when (destination.id) {
                R.id.signInFragment,
                R.id.signUpFragment,
                R.id.introFragment,
                R.id.questionFragment -> false
                else -> true
            }
            toolbar.isVisible = isToolbarVisible
        }
}