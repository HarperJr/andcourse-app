package com.conceptic.andcourse.presentation.questionnaire.question

import android.os.Bundle
import android.view.View
import com.conceptic.andcourse.R
import com.conceptic.andcourse.presentation.base.BaseFragment
import com.conceptic.andcourse.presentation.ext.createScope
import com.conceptic.andcourse.presentation.questionnaire.question.adapter.QuestionsAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fargment_question.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ticker
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope

@ObsoleteCoroutinesApi
class QuestionFragment : BaseFragment<QuestionViewModel>(R.layout.fargment_question) {
    override val scope: Scope = createScope(QUESTION_SCOPE)
    override val viewModel: QuestionViewModel by scope.viewModel(this)

    private val backwardsJob = SupervisorJob()
    private val backwardsScope = CoroutineScope(Dispatchers.Main + backwardsJob)
    private val backPressedTimer = ticker(BACK_PRESSED_DELAY_MILLIS)

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
            questionnaireCompleteLiveData.observe({ lifecycle }) { showQuestionnaireCompletion() }
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

    /**
     * Warning!!! Unstable using here
     */
    override fun onBackPressed(): Boolean {
        return if (!backPressed) {
            backPressed = true
            Snackbar.make(question_view_pager, R.string.questionnaire_back_pressed_message, Snackbar.LENGTH_SHORT)
                .show()
            backwardsJob.cancelChildren()
            backwardsScope.launch(Dispatchers.IO) {
                for (event in backPressedTimer)
                    backPressed = false
            }
            false
        } else super.onBackPressed()
    }

    private fun showQuestionnaireCompletion() {

    }

    companion object {
        private const val QUESTION_SCOPE = "question_scope"
        private const val BACK_PRESSED_DELAY_MILLIS = 2500L
    }
}