package com.sandy.memorizingvoca.ui.feature.quiz_result

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.sandy.memorizingvoca.data.model.Vocabulary
import com.sandy.memorizingvoca.data.repository.BookmarkRepository
import com.sandy.memorizingvoca.data.repository.GetQuizRepository
import com.sandy.memorizingvoca.data.repository.QuizRepository
import com.sandy.memorizingvoca.ui.feature.quiz_result.navigation.QuizResultRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
internal class QuizResultViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getQuizRepository: GetQuizRepository,
    private val quizRepository: QuizRepository,
    private val bookmarkRepository: BookmarkRepository,
): ViewModel() {

    private val date = savedStateHandle.toRoute<QuizResultRoute>().date

    private val _quizResultUiState = MutableStateFlow<QuizResultUiState?>(null)
    val quizResultUiState = _quizResultUiState.asStateFlow()

    val incorrectedVocaList = getQuizRepository.getWrongVocaList(date).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList(),
    )

    init {
        viewModelScope.launch {
            getQuizRepository.getQuizResult(date).run {
                val correctCount = totalCount - wrongCount
                val percentage = calculatePercentage(totalCount, correctCount)
                val quizResultState = QuizResultUiState(
                    quiz = this,
                    title = getQuizResultTitle(day),
                    date = getQuizResultDate(date),
                    correctCount = correctCount,
                    incorrectCount = wrongCount,
                    totalCount = totalCount,
                    percentage = percentage,
                    deleted = false,
                )
                _quizResultUiState.update { quizResultState }
            }
        }
    }

    private fun calculatePercentage(totalCount: Int, correctCount: Int): Int {
        if(totalCount == 0) return 0
        val result = (correctCount.toDouble() / totalCount.toDouble()) * 100
        return result.toInt()
    }

    private fun getQuizResultTitle(day: Int): String {
        return when {
            day > 0 -> "Day " + String.format("%02d", day)
            else -> "북마크"
        }
    }

    private fun getQuizResultDate(dateStr: String): String {
        val dateTime = LocalDateTime.parse(dateStr)
        val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm")
        return dateTime.format(formatter)
    }

    fun deleteQuizResult() = viewModelScope.launch {
        quizResultUiState.value?.let {
            quizRepository.deleteQuiz(it.quiz)
        }
    }.invokeOnCompletion {
        _quizResultUiState.value = quizResultUiState.value?.copy(deleted = true)
    }

    fun addMultipleBookmark() = viewModelScope.launch {
        val multipleList = incorrectedVocaList.value.filter { !it.bookmarked }.map {
            it.copy(bookmarked = true)
        }
        if(multipleList.isEmpty()) return@launch
        bookmarkRepository.addMutipleBookmark(multipleList)
    }

    fun updateBookmark(voca: Vocabulary, bookmarked: Boolean) = viewModelScope.launch {
        when {
            bookmarked -> addBookmarkVoca(voca)
            else -> deleteBookmarkVoca(voca)
        }
    }

    private suspend fun addBookmarkVoca(voca: Vocabulary) {
        bookmarkRepository.addBookmark(
            vocaId = voca.vocaId,
            day = voca.day,
            word = voca.word,
            meaning = voca.meaning,
            highlighted = voca.highlighted,
        )
    }

    private suspend fun deleteBookmarkVoca(voca: Vocabulary) {
        bookmarkRepository.deleteBookmark(
            vocaId = voca.vocaId,
            day = voca.day,
            word = voca.word,
            meaning = voca.meaning,
            highlighted = voca.highlighted,
        )
    }

}