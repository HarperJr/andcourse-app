package com.conceptic.andcourse.presentation.personal

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.conceptic.andcourse.R
import com.conceptic.andcourse.presentation.base.BaseFragment
import com.conceptic.andcourse.presentation.view.LoadingProgressDialog
import kotlinx.android.synthetic.main.fragment_personal.*
import kotlinx.android.synthetic.main.personal_placeholder.*
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class PersonalPageFragment : BaseFragment<PersonalViewModel>(R.layout.fragment_personal) {
    override val viewModel: PersonalViewModel by currentScope.viewModel(this)
    private val dateFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(viewModel) {
            loadingLiveData.observe({ lifecycle }) { setLoadingVisible(it) }
            logoutSuccessfulLiveDate.observe({ lifecycle }) { navController.popBackStack() }
            credentialsLiveData.observe({ lifecycle }) { credentials ->
                credentials?.let {
                    personal_mail.text = getString(R.string.personal_mail, credentials.mail)
                    personal_gender.text = getString(R.string.personal_gender, credentials.gender.name)
                    personal_date_birth.text =
                        getString(R.string.personal_date_birth, dateFormatter.format(credentials.dateBirth))
                    personal_date_registered.text =
                        getString(R.string.personal_date_registered, dateFormatter.format(credentials.dateRegistered))
                    personal_page_container.isVisible = true
                    personal_placeholder.isVisible = false
                } ?: run {
                    personal_page_container.isVisible = false
                    personal_placeholder.isVisible = true
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        personal_retry_btn.setOnClickListener { viewModel.onRetryBtnClicked() }
        personal_logout.setOnClickListener { viewModel.onLogoutBtnClicked() }
    }

    override fun handleError(message: String) {
        personal_placeholder.isVisible = true
    }

    private fun setLoadingVisible(visible: Boolean) = LoadingProgressDialog.setVisible(this, visible)
}