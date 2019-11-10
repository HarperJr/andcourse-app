package com.conceptic.andcourse.presentation.questionnaire

import androidx.lifecycle.MutableLiveData
import com.conceptic.andcourse.presentation.base.BaseViewModel
import com.conceptic.andcourse.usecase.questionnaire.BeginQuestionnaireCase

class QuestionnaireBeginViewModel(
    private val beginQuestionnaireCase: BeginQuestionnaireCase
) : BaseViewModel() {
    val beginSuccessLiveData = MutableLiveData<Unit>()

    override fun onStart() {}

    fun onBeginBtnClicked() {
        beginQuestionnaireCase
            .execute(Unit)
            .subscribe({
                beginSuccessLiveData.value = Unit
            }, {

            })
            .disposeWhenCleared()
    }
}