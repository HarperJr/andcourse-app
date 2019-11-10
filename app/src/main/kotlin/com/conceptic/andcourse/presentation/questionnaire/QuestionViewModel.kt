package com.conceptic.andcourse.presentation.questionnaire

import com.conceptic.andcourse.presentation.base.BaseViewModel
import com.conceptic.andcourse.usecase.questionnaire.NextQuestionCase

class QuestionViewModel(
    private val nextQuestionCase: NextQuestionCase
) : BaseViewModel() {
    override fun onStart() {}
}