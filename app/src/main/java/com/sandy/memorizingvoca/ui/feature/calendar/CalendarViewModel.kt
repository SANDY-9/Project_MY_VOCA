package com.sandy.memorizingvoca.ui.feature.calendar

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandy.memorizingvoca.data.model.Date
import com.sandy.memorizingvoca.data.model.VocaQuiz
import com.sandy.memorizingvoca.data.repository.GetQuizRepository
import com.sandy.memorizingvoca.data.repository.QuizRepository
import com.sandy.memorizingvoca.utils.DateUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class CalendarViewModel @Inject constructor(
    private val getQuizRepository: GetQuizRepository,
    private val quizRepository: QuizRepository,
): ViewModel() {

    private val _calendarUiState = MutableStateFlow(CalendarUiState())
    val calendarUiState = _calendarUiState.asStateFlow()

    init {
        getQuizRepository.getQuizListForCalendar()
            .onEach { quizCalendar ->
                _calendarUiState.update {
                    it.copy(
                        quizCalendar = quizCalendar,
                        quizList = quizCalendar[it.selectedDate] ?: emptyList()
                    )
                }
            }.launchIn(viewModelScope)
    }

    fun deleteQuiz(quiz: VocaQuiz) = viewModelScope.launch {
        quizRepository.deleteQuiz(quiz)
    }

    fun deleteMultipleQuiz() = viewModelScope.launch {
        quizRepository.deleteMultipleQuiz(calendarUiState.value.quizList)
    }

    fun clearCalendar() = viewModelScope.launch {
        val quizCalendar = calendarUiState.value.quizCalendar.flatMap { it.value }
        quizRepository.deleteMultipleQuiz(quizCalendar)
    }

    fun onPageChange(page: Int) {
        _calendarUiState.value = calendarUiState.value.run {
            val newCalendar = calendarList[page]
            val firstDay = DateUtils.getFirstDay(newCalendar.year, newCalendar.month).let {
                Date(
                    localDate = it,
                    weekIndex = DateUtils.getWeekIndexOfMonthForLocale(it),
                )
                Date(localDate = it,)
            }
            copy(
                calendar = newCalendar,
                currentCalendarPage = page,
                selectedDate = firstDay,
                quizList = quizCalendar[firstDay] ?: emptyList(),
            )
        }
    }

    fun onDateSelect(date: Date) {
        Log.e("확인", "onDateSelect: 실행안됨?", )
        _calendarUiState.update {
            it.copy(
                selectedDate = date,
                quizList = it.quizCalendar[date] ?: emptyList(),
            )
        }
    }

}