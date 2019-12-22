package com.conceptic.andcourse.presentation.questionnaire.question

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.navigation.fragment.findNavController
import com.conceptic.andcourse.R
import com.conceptic.andcourse.data.model.Question
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
            questionsLiveData.observe({ lifecycle }) { questions ->
                adapter.items = questions
                onQuestionsDefined(questions)
            }
            questionnaireCompleteLiveData.observe({ lifecycle }) {
                findNavController().popBackStack(R.id.summaryFragment, true)
            }
            currentQuestionLiveData.observe({ lifecycle }) { currentQuestion ->
                question_view_pager.setCurrentItem(currentQuestion.order, true)
                onCurrentQuestionChanged(currentQuestion)
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
        } else navController.popBackStack(R.id.summaryFragment, false)
        return false
    }

    private fun onCurrentQuestionChanged(question: Question) {
        val newProgress = question.order
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            passing_progress.setProgress(newProgress, true)
        } else passing_progress.progress = newProgress
    }

    private fun onQuestionsDefined(questions: List<Question>) {
        passing_progress.max = questions.size
    }

    companion object {
        private const val BACK_PRESSED_DELAY_MILLIS = 2500L
    }
}