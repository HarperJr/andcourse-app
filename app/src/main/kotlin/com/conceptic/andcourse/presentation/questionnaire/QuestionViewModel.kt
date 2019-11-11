package com.conceptic.andcourse.presentation.questionnaire

import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.conceptic.andcourse.data.api.ApiException
import com.conceptic.andcourse.data.model.Answer
import com.conceptic.andcourse.data.repos.QuestionRepository
import com.conceptic.andcourse.presentation.base.BaseViewModel
import com.conceptic.andcourse.usecase.questionnaire.NextQuestionCase
import com.conceptic.andcourse.usecase.questionnaire.NextQuestionParams
import kotlinx.coroutines.*

class QuestionViewModel(
    questionRepository: QuestionRepository,
    private val nextQuestionCase: NextQuestionCase
) : BaseViewModel() {
    val questionsLiveData = liveData {
        coroutineScope {
            val questions = async(Dispatchers.IO) { questionRepository.questions() }
            withContext(Dispatchers.Main) {
                runCatching { questions.await() }
                    .onSuccess { questions -> emit(questions) }
                    .onFailure { throwable ->
                        ApiException.letFromThrowable(throwable) { message -> errorMessages.value = message }
                    }
            }
        }
    }

    fun onQuestionAnswered(answer: Answer) = viewModelScope.launch(Dispatchers.IO) {
        runCatching {
            nextQuestionCase.execute(NextQuestionParams())
        }.onSuccess { }
            .onFailure { throwable ->
                ApiException.letFromThrowable(throwable) { errorMessages.value = it }
            }
    }
}

