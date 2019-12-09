package com.conceptic.andcourse.presentation.questionnaire.question

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.conceptic.andcourse.data.api.ApiException
import com.conceptic.andcourse.data.model.Answer
import com.conceptic.andcourse.data.model.Question
import com.conceptic.andcourse.data.repos.QuestionRepository
import com.conceptic.andcourse.presentation.base.BaseViewModel
import com.conceptic.andcourse.usecase.questionnaire.completion.CompleteQuestionnaireCase
import com.conceptic.andcourse.usecase.questionnaire.next.NextQuestionCase
import com.conceptic.andcourse.usecase.questionnaire.next.NextQuestionParams
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QuestionViewModel(
    private val questionRepository: QuestionRepository,
    private val nextQuestionCase: NextQuestionCase,
    private val completeQuestionnaireCase: CompleteQuestionnaireCase
) : BaseViewModel() {
    val questionsLiveData = liveData(viewModelScope.coroutineContext) {
        coroutineScope {
            runCatching {
                withContext(Dispatchers.IO) { questionRepository.questions() }
            }.onSuccess { questions -> emit(questions) }
                .onFailure { throwable ->
                    ApiException.letFromThrowable(throwable) { message -> errorMessages.value = message }
                }
        }
    }
    val currentQuestionLiveData = MutableLiveData<Question>()
    val questionnaireCompleteLiveData = MutableLiveData<Unit>()

    fun onQuestionAnswered(answer: Answer) = viewModelScope.launch(Dispatchers.IO) {
        runCatching {
            nextQuestionCase.execute(NextQuestionParams(answer.ordinal))
        }.onSuccess { nextQuestion ->
            nextQuestion?.let {
                currentQuestionLiveData.postValue(questionRepository.find(it))
            } ?: completeQuestionnaire()
        }.onFailure { throwable ->
            ApiException.letFromThrowable(throwable) { errorMessages.postValue(it) }
        }
    }

    private suspend fun completeQuestionnaire() {
        coroutineScope {
            runCatching {
                completeQuestionnaireCase.execute(Unit)
            }.onSuccess { questionnaireCompleteLiveData.postValue(Unit) }
                .onFailure { throwable ->
                    ApiException.letFromThrowable(throwable) { errorMessages.postValue(it) }
                }
        }
    }
}