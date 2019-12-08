package com.conceptic.andcourse.presentation.questionnaire.begin

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.conceptic.andcourse.R
import com.conceptic.andcourse.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_questionnaire_intro.*
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuestionnaireBeginFragment :
    BaseFragment<QuestionnaireBeginViewModel>(R.layout.fragment_questionnaire_intro) {
    override val viewModel: QuestionnaireBeginViewModel by currentScope.viewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.beginSuccessLiveData.observe({ lifecycle }) {
            findNavController().navigate(R.id.action_questionnaireBeginFragment_to_questionFragment)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        questionnaire_begin.setOnClickListener {
            viewModel.onBeginBtnClicked()
        }
    }

    companion object {
        private const val QUESTIONNAIRE_BEGIN_SCOPE = "questionnaire_begin"
    }
}