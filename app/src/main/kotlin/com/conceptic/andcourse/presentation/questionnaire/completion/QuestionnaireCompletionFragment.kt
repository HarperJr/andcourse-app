package com.conceptic.andcourse.presentation.questionnaire.completion

import android.os.Bundle
import android.view.View
import com.conceptic.andcourse.R
import com.conceptic.andcourse.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_qestionnaire_completion.*
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuestionnaireCompletionFragment :
    BaseFragment<QuestionnaireCompletionViewModel>(R.layout.fragment_qestionnaire_completion) {
    override val viewModel: QuestionnaireCompletionViewModel by currentScope.viewModel(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        complete_btn.setOnClickListener { viewModel.onCompleteBtnClicked() }
        viewModel.completionSuccessLiveData.observe({ lifecycle }) {
            //TODO handle
        }
    }
}