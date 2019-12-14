package com.conceptic.andcourse.presentation.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<VM : BaseViewModel>(@LayoutRes layout: Int) : Fragment(layout) {
    protected abstract val viewModel: VM
    protected val navController: NavController
        get() = findNavController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.errorMessageLiveData.observe({ lifecycle }) { message ->
            showSnack(message, Snackbar.LENGTH_SHORT)
        }
    }

    open fun onBackPressed(): Boolean = true

    protected fun showSnack(
        @StringRes messageRes: Int, type: Int,
        @StringRes actionRes: Int? = null, action: ((view: View) -> Unit)? = null
    ) {
        return showSnack(getString(messageRes), type, actionRes, action)
    }

    protected fun showSnack(
        message: String, type: Int,
        @StringRes actionRes: Int? = null, action: ((view: View) -> Unit)? = null
    ) = Snackbar.make(requireView(), message, type)
        .also { snack ->
            actionRes?.let { res -> snack.setAction(res) { action?.invoke(it) } }
        }.show()
}