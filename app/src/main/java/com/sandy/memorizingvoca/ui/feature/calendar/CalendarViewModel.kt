package com.sandy.memorizingvoca.ui.feature.calendar

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

    private val current
        get() = calendarUiState.value

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
        quizRepository.deleteMultipleQuiz(current.quizList)
    }

    fun clearCalendar() = viewModelScope.launch {
        val quizCalendar = current.quizCalendar.flatMap { it.value }
        quizRepository.deleteMultipleQuiz(quizCalendar)
    }

    fun onCalendarPageChange(page: Int) {
        _calendarUiState.value = current.run {
            val newCalendar = allCalendarList[page]
            val firstDay = Date(
                localDate = DateUtils.getFirstDay(newCalendar.year, newCalendar.month),
                )
            val newSelectedDate = selectedDate.takeIf {
                calendar.month != selectedDate.month
            } ?: today.takeIf {
                firstDay.month == today.month && firstDay.year == today.year
            } ?: firstDay
            copy(
                calendar = newCalendar,
                currentCalendarPage = page,
                selectedDate = newSelectedDate,
                quizList = quizCalendar[newSelectedDate] ?: emptyList(),
            )
        }
    }

    fun onListPageChange(page: Int) {
        val curPage = current.currentListPage
        if(page == curPage) return

        val newSelectDate = Date(
            localDate = current.selectedDate.localDate.run {
                if(page > curPage) plusDays(1) else minusDays(1)
            }
        )
        _calendarUiState.value = current.run {
            copy(
                currentListPage = page,
                selectedDate = newSelectDate,
                quizList = quizCalendar[newSelectDate] ?: emptyList(),
            )
        }
        onDateSelect(newSelectDate)
    }

    fun onDateSelect(date: Date) {
        val nextCalendarPage = calculateNextCalendarPage(date)
        _calendarUiState.update {
            it.copy(
                selectedDate = date,
                currentCalendarPage = nextCalendarPage,
                quizList = it.quizCalendar[date] ?: emptyList(),
                currentListPage = current.allDateList[date] ?: current.currentListPage,
            )
        }
    }

    private fun calculateNextCalendarPage(date: Date): Int {
        val currentMonth = current.calendar.month
        val selectedMonth = date.month
        val currentPage = current.currentCalendarPage

        return currentPage + when {
            currentMonth < selectedMonth -> {
                if (currentMonth == 1 && selectedMonth == 12) -1 else 1
            }
            currentMonth > selectedMonth -> {
                when {
                    currentMonth == 12 && selectedMonth == 1 -> 1
                    currentPage == 0 -> 0
                    else -> -1
                }
            }
            else -> 0
        }
    }
}