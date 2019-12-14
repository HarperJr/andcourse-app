package com.conceptic.andcourse.presentation.questionnaire.intro

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.conceptic.andcourse.R
import com.conceptic.andcourse.presentation.base.BaseFragment
import com.conceptic.andcourse.presentation.questionnaire.intro.adapter.IntroPageItem
import com.conceptic.andcourse.presentation.questionnaire.intro.adapter.IntroPagerAdapter
import com.conceptic.andcourse.presentation.view.LoadingProgressDialog
import kotlinx.android.synthetic.main.fragment_intro.*
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel

class IntroFragment :
    BaseFragment<IntroViewModel>(R.layout.fragment_intro) {
    override val viewModel: IntroViewModel by currentScope.viewModel(this)

    private val introPages = listOf(
        IntroPageItem(
            R.drawable.test_description,
            R.string.intro_test_description
        ),
        IntroPageItem(
            R.drawable.test_description,
            R.string.intro_test_description
        ),
        IntroPageItem(
            R.drawable.test_description,
            R.string.intro_test_description
        ),
        IntroPageItem(
            R.drawable.test_description,
            R.string.intro_test_description
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadingLiveData.observe({ lifecycle }) { setLoadingVisible(it) }
        viewModel.beginSuccessLiveData.observe({ lifecycle }) {
            findNavController().navigate(R.id.action_introFragment_to_questionFragment)
        }
    }

    private fun setLoadingVisible(visible: Boolean) = LoadingProgressDialog.setVisible(this, visible)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        intro_view_pager.adapter = IntroPagerAdapter(requireContext(), introPages)
        intro_pass_btn.setOnClickListener { viewModel.onBeginBtnClicked() }
    }
}