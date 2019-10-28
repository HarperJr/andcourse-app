package com.conceptic.andcourse.presentation.questionnaire

import android.os.Bundle
import android.view.View
import com.conceptic.andcourse.R
import com.conceptic.andcourse.presentation.base.BaseFragment
import com.conceptic.andcourse.presentation.ext.createScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope

class QuestionFragment : BaseFragment<QuestionViewModel>(R.layout.fargment_question) {
    override val scope: Scope = createScope(QUESTION_SCOPE)
    override val viewModel: QuestionViewModel by scope.viewModel(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        private const val QUESTION_SCOPE = "question_scope"
    }
}