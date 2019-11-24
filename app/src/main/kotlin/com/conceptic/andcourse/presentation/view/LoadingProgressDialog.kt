package com.conceptic.andcourse.presentation.view

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.conceptic.andcourse.R

class LoadingProgressDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setView(R.layout.dialog_progress)
            .setCancelable(false)
            .create()
    }

    companion object {
        private const val TAG = "progress_dialog"

        fun setVisible(fragment: Fragment, visible: Boolean) = with(fragment.childFragmentManager) {
            val progressDialog = findFragmentByTag(TAG) as LoadingProgressDialog?
            if (visible) progressDialog ?: run {
                LoadingProgressDialog().show(
                    this@with,
                    TAG
                )
            } else progressDialog?.dismiss()
        }
    }
}