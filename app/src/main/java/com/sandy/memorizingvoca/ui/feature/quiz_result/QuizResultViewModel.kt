package com.sandy.memorizingvoca.ui.feature.quiz_result

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.sandy.memorizingvoca.data.repository.GetQuizRepository
import com.sandy.memorizingvoca.data.repository.QuizRepository
import com.sandy.memorizingvoca.ui.feature.quiz_result.navigation.QuizResultRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
internal class QuizResultViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getQuizRepository: GetQuizRepository,
    private val quizRepository: QuizRepository,
): ViewModel() {

    private val date = savedStateHandle.toRoute<QuizResultRoute>().date

    val quizResult = getQuizRepository.getQuizResult(date).map {
        val incorrectCount = it.wrongCount
        val correctCount = it.totalCount - incorrectCount
        val totalCollection = it.totalCount
        QuizResultUiState(
            title = getQuizResultTitle(it.day),
            date = getQuizResultDate(it.date),
            correctCount = correctCount,
            incorrectCount = incorrectCount,
            totalCount = totalCollection,
            percentage = (correctCount / totalCollection) * 100
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = null,
    )

    private fun getQuizResultTitle(day: Int): String {
        return when {
            day > 0 -> "Day " + String.format("%02d", day)
            else -> "Bookmark"
        }
    }

    private fun getQuizResultDate(dateStr: String): String {
        val dateTime = LocalDateTime.parse(dateStr)
        val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm")
        return dateTime.format(formatter)
    }

    val incorrectedVocaList = getQuizRepository.getWrongVocaList(date).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList(),
    )

}