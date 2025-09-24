package com.sandy.memorizingvoca.ui.feature.quiz1

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.sandy.memorizingvoca.data.model.Vocabulary
import com.sandy.memorizingvoca.data.repository.BookmarkRepository
import com.sandy.memorizingvoca.data.repository.GetVocabularyRepository
import com.sandy.memorizingvoca.ui.feature.quiz1.navigation.Quiz1Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class Quiz1ViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getVocabularyRepository: GetVocabularyRepository,
    private val getBookmarkRepository: BookmarkRepository,
): ViewModel() {

    private val _quiz1UiState = MutableStateFlow(Quiz1UiState())
    val quiz1UiState = _quiz1UiState.asStateFlow()

    private val _questionState = MutableStateFlow(Quiz1QuestionState())
    val questionState = _questionState.asStateFlow()

    init {
        viewModelScope.launch {
            initQuiz1UiState()
            quiz1UiState.map { it.answerState }.collectLatest { answer ->
                when(answer) {
                    AnswerState.NONE -> nextQuestion()
                    AnswerState.SOLVING_QUESTIONS -> {
                        delay(5000L) // 시간제한
                        if(quiz1UiState.value.answerState == AnswerState.SOLVING_QUESTIONS) checkAnswer(null)
                    }
                    AnswerState.CORRECT, AnswerState.INCORRECT -> {
                        delay(2000L)
                        nextQuestion()
                    }
                }
            }
        }
    }

    private suspend fun initQuiz1UiState() {
        val day = savedStateHandle.toRoute<Quiz1Route>().day
        val vocaList = downloadVocaList(day)
        _quiz1UiState.update {
            Quiz1UiState(
                title = getQuiz1Title(day),
                vocaList = vocaList,
                correctCount = 0,
                totalCount = vocaList.size,
            )
        }
    }

    private fun getQuiz1Title(day: Int): String {
        return when {
            day > 0 -> "Day " + String.format("%02d", day)
            else -> "Bookmark"
        }
    }

    private suspend fun downloadVocaList(day: Int): List<Vocabulary> {
        return when {
            day > 0 -> getBookmarkRepository.getBookmarkList().first().flatMap { it.value }
            else -> getVocabularyRepository.getVocaList(day).first()
        }.shuffled()
    }

    private fun nextQuestion() {
        updateQuestionState()
        updateAnswerState(AnswerState.SOLVING_QUESTIONS)
    }

    private fun updateQuestionState() {
        val currentIndex = questionState.value.index ?: 0
        val nextIndex = currentIndex + 1
        if(nextIndex < quiz1UiState.value.totalCount) return

        val answer = quiz1UiState.value.vocaList[nextIndex]
        val options = generateOptions(answer.meaning)
        _questionState.value = Quiz1QuestionState(
            index = nextIndex,
            question = answer.word,
            options = options,
            answerIndex = options.indexOf(answer.meaning),
        )
    }

    private fun generateOptions(answer: String): List<String> {
        val incorrectOptions = quiz1UiState.value.vocaList
            .filter { it.word != answer }
            .shuffled()
            .take(3)
            .map { it.meaning }
        return (incorrectOptions + answer).shuffled()
    }

    private fun updateAnswerState(answerState: AnswerState) {
        _quiz1UiState.update {
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
    }

}
