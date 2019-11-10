package com.conceptic.andcourse.presentation.questionnaire

import androidx.lifecycle.LiveData
import com.conceptic.andcourse.data.api.ApiException
import com.conceptic.andcourse.data.model.Answer
import com.conceptic.andcourse.data.model.Question
import com.conceptic.andcourse.data.repos.QuestionRepository
import com.conceptic.andcourse.presentation.base.BaseViewModel
import com.conceptic.andcourse.usecase.questionnaire.NextQuestionCase
import com.conceptic.andcourse.usecase.questionnaire.NextQuestionParams
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers

class QuestionViewModel(
    questionRepository: QuestionRepository,
    private val nextQuestionCase: NextQuestionCase
) : BaseViewModel() {
    val questionsLiveData = QuestionsLiveData(questionRepository) {
        ApiException.letFromThrowable(it) { message -> errorMessages.value = message }
    }

    override fun onStart() {}

    fun onQuestionAnswered(answer: Answer) {
        nextQuestionCase.execute(NextQuestionParams())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

            }, { throwable ->
                ApiException.letFromThrowable(throwable) { errorMessages.value = it }
            })
            .disposeWhenCleared()
    }
}

class QuestionsLiveData(
    private val questionRepository: QuestionRepository,
    private val onError: (Throwable) -> Unit
) :
    LiveData<List<Question>>() {
    private var questionsDisposable = Disposables.disposed()

    override fun onActive() {
        questionsDisposable = questionRepository.all()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ questions ->
                value = questions
            }, { onError.invoke(it) })
    }

    override fun onInactive() {
        questionsDisposable.dispose()
        super.onInactive()
    }
}