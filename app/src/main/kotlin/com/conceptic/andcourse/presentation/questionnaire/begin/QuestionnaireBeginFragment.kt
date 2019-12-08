package com.conceptic.andcourse.presentation.questionnaire.begin

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.conceptic.andcourse.R
import com.conceptic.andcourse.presentation.base.BaseFragment
import com.conceptic.andcourse.presentation.ext.createScope
import kotlinx.android.synthetic.main.fragment_qurstionnaire_intro.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope

class QuestionnaireBeginFragment :
    BaseFragment<QuestionnaireBeginViewModel>(R.layout.fragment_qurstionnaire_intro) {
    override val scope: Scope = createScope(QUESTIONNAIRE_BEGIN_SCOPE)
    override val viewModel: QuestionnaireBeginViewModel by scope.viewModel(this)

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