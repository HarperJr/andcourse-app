package com.conceptic.andcourse.presentation.questionnaire

import android.os.Bundle
import android.view.View
import com.conceptic.andcourse.R
import com.conceptic.andcourse.presentation.base.BaseFragment
import com.conceptic.andcourse.presentation.ext.createScope
import com.conceptic.andcourse.presentation.questionnaire.adapter.QuestionsAdapter
import kotlinx.android.synthetic.main.fargment_question.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope

class QuestionFragment : BaseFragment<QuestionViewModel>(R.layout.fargment_question) {
    override val scope: Scope = createScope(QUESTION_SCOPE)
    override val viewModel: QuestionViewModel by scope.viewModel(this)

    private val adapter by lazy {
        QuestionsAdapter(requireContext()) { answer ->
            viewModel.onQuestionAnswered(answer)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.questionsLiveData.observe({ lifecycle }) { questions ->
            adapter.items = questions
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        question_view_pager.apply {
            adapter = this@QuestionFragment.adapter
            isUserInputEnabled = false
        }
    }

    companion object {
        private const val QUESTION_SCOPE = "question_scope"
    }
}