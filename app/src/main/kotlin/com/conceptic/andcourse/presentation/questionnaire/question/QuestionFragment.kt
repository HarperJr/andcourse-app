package com.conceptic.andcourse.presentation.questionnaire.question

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.navigation.fragment.findNavController
import com.conceptic.andcourse.R
import com.conceptic.andcourse.presentation.base.BaseFragment
import com.conceptic.andcourse.presentation.questionnaire.question.adapter.QuestionsAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fargment_question.*
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuestionFragment : BaseFragment<QuestionViewModel>(R.layout.fargment_question) {
    override val viewModel: QuestionViewModel by currentScope.viewModel(this)

    private val handler = Handler()
    private var backPressed = false

    private val adapter by lazy {
        QuestionsAdapter(requireContext()) { answer ->
            viewModel.onQuestionAnswered(answer)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.apply {
            questionsLiveData.observe({ lifecycle }) { questions -> adapter.items = questions }
            questionnaireCompleteLiveData.observe({ lifecycle }) {
                findNavController().navigate(R.id.action_questionFragment_to_summaryFragment)
            }

            currentQuestionLiveData.observe({ lifecycle }) { currentQuestion ->
                question_view_pager.setCurrentItem(currentQuestion.order, true)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        question_view_pager.apply {
            adapter = this@QuestionFragment.adapter
            isUserInputEnabled = false
        }
    }

    override fun onBackPressed(): Boolean {
        if (!backPressed) {
            backPressed = true
            showSnack(R.string.questionnaire_back_pressed_message, Snackbar.LENGTH_SHORT)
            handler.postDelayed({ backPressed = false }, BACK_PRESSED_DELAY_MILLIS)
            return false
        }
        return super.onBackPressed()
    }

    companion object {
        private const val BACK_PRESSED_DELAY_MILLIS = 2500L
    }
}