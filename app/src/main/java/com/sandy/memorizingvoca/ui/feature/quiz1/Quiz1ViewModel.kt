package com.sandy.memorizingvoca.ui.feature.quiz1

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.sandy.memorizingvoca.data.model.Vocabulary
import com.sandy.memorizingvoca.data.repository.BookmarkRepository
import com.sandy.memorizingvoca.data.repository.GetVocabularyRepository
import com.sandy.memorizingvoca.data.repository.QuizRepository
import com.sandy.memorizingvoca.ui.feature.quiz1.navigation.Quiz1Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
internal class Quiz1ViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getVocabularyRepository: GetVocabularyRepository,
    private val getBookmarkRepository: BookmarkRepository,
    private val quizRepository: QuizRepository,
): ViewModel() {

    private val day = savedStateHandle.toRoute<Quiz1Route>().day

    private val _quiz1State = MutableStateFlow(Quiz1State())
    val quiz1State = _quiz1State.asStateFlow()

    private val _questionState = MutableStateFlow(Quiz1QuestionState())
    val questionState = _questionState.asStateFlow()

    init {
        viewModelScope.launch {
            quiz1State.map { it.answerState }.collectLatest { answer ->
                when(answer) {
                    AnswerState.NONE -> {
                        initQuiz1UiState()
                        nextQuestion()
                    }
                    AnswerState.SOLVING_QUESTIONS -> {
                        delay(QUIZ1_TIME_OUT.toLong()) // 시간제한
                        if(quiz1State.value.answerState == AnswerState.SOLVING_QUESTIONS) checkAnswer(null)
                    }
                    AnswerState.CORRECT, AnswerState.INCORRECT -> {
                        delay(1000L)
                        nextQuestion()
                    }
                    AnswerState.DONE -> {
                        requestQuizResult()
                    }
                }
            }
        }
    }

    private suspend fun initQuiz1UiState() {
        val vocaList = downloadVocaList(day).take(10)
        _quiz1State.value = Quiz1State(
            title = getQuiz1Title(day),
            vocaList = vocaList,
            correctCount = 0,
            totalCount = vocaList.size,
            incorrectedList = emptyList(),
            quizDate = LocalDateTime.now().toString()
        )
    }

    private fun getQuiz1Title(day: Int): String {
        return when {
            day > 0 -> "Day " + String.format("%02d", day)
            else -> "Bookmark"
        }
    }

    private suspend fun downloadVocaList(day: Int): List<Vocabulary> {
        return when {
            day > 0 -> getVocabularyRepository.getVocaList(day).first()
            else -> getBookmarkRepository.getBookmarkList().first().flatMap { it.value }
        }.shuffled()
    }

    private fun nextQuestion() {
        if(questionState.value.index == quiz1State.value.vocaList.lastIndex) {
            updateAnswerState(AnswerState.DONE)
            return
        }
        updateQuestionState()
        updateAnswerState(AnswerState.SOLVING_QUESTIONS)
    }

    private fun updateQuestionState() = _questionState.update { current ->
        val nextIndex = (current.index ?: -1) + 1
        val answerVoca = quiz1State.value.vocaList[nextIndex]
        val options = generateOptions(answerVoca.meaning)
        Quiz1QuestionState(
            index = nextIndex,
            question = answerVoca.word,
            questionNumTitle = String.format("%02d.", nextIndex + 1),
            options = options,
            answerIndex = options.indexOf(answerVoca.meaning),
            answerVoca = answerVoca,
        )
    }

    private fun generateOptions(answer: String): List<String> {
        val incorrectOptions = quiz1State.value.vocaList
            .filter { it.meaning != answer }
            .shuffled()
            .take(3)
            .map { it.meaning }
        return (incorrectOptions + answer).shuffled()
    }

    private fun updateAnswerState(answerState: AnswerState) {
        _quiz1State.update {
            val currentScore = it.correctCount
            it.copy(
                correctCount = if(answerState == AnswerState.CORRECT) currentScore + 1 else currentScore,
                answerState = answerState,
            )
        }
    }

    fun checkAnswer(selectIndex: Int?) {
        val correct = questionState.value.answerIndex == selectIndex
        val answer = if(correct) AnswerState.CORRECT else AnswerState.INCORRECT
        updateAnswerState(answer)
        updateIncorrectedVocaList(answer)
    }

    private fun updateIncorrectedVocaList(answer: AnswerState) {
        if(answer == AnswerState.INCORRECT) {
            val voca = questionState.value.answerVoca ?: return
            _quiz1State.update {
                it.copy(
                    incorrectedList = it.incorrectedList.toMutableList().apply { add(voca) }
                )
            }
        }
    }

    private fun requestQuizResult() = CoroutineScope(Dispatchers.IO).launch {
        val quiz1State = quiz1State.value
        quizRepository.addNewQuizResult(
            date = quiz1State.quizDate ?: return@launch,
            day = day,
            totalCount = quiz1State.totalCount,
            incorrectedVocaId = quiz1State.incorrectedList.map { it.vocaId },
        )
    }

}
