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
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
internal class CalendarViewModel @Inject constructor(
    getQuizRepository: GetQuizRepository,
    private val quizRepository: QuizRepository,
): ViewModel() {

    private val _calendarUiState = MutableStateFlow(CalendarUiState())
    val calendarUiState = _calendarUiState.asStateFlow()

    private val current
        get() = calendarUiState.value

    init {
        getQuizRepository.getQuizListForCalendar()
            .onEach { quizCalendar ->
                val quizList = current.allDateList.map { date ->
                    quizCalendar[date] ?: emptyList()
                }
                _calendarUiState.update {
                    it.copy(
                        quizCalendar = quizCalendar,
                        quizList = quizList,
                    )
                }
            }.launchIn(viewModelScope)
    }

    fun deleteQuiz(quiz: VocaQuiz) = viewModelScope.launch {
        quizRepository.deleteQuiz(quiz)
    }

    fun deleteMultipleQuiz() = viewModelScope.launch {
        quizRepository.deleteMultipleQuiz(current.quizList[current.currentListPage])
    }

    fun clearCalendar() = viewModelScope.launch {
        val quizList = current.quizCalendar.filterKeys {
            it.month == current.calendar.month && it.year == current.calendar.year
        }.flatMap { it.value }
        quizRepository.deleteMultipleQuiz(quizList)
    }

    fun onCalendarTypeChange(type: CalendarType) {
        _calendarUiState.value = current.copy(
            calendarType = type,
        )
    }

    fun onCalendarFractionChange(fraction: Float) {
        _calendarUiState.value = current.copy(
            calendarFraction = fraction,
        )
    }

    fun onCalendarPageChange(page: Int) {
        if(page == current.currentCalendarPage) return

        val newSelectedDate = getNewCalendarSelectedDate(page)
        onDateSelect(newSelectedDate)
    }

    private fun getNewCalendarSelectedDate(page: Int) = with(current) {
        val newCalendar = allCalendarList[page]
        val firstDay = Date(
            localDate = DateUtils.getFirstDay(newCalendar.year, newCalendar.month),
        )
        return@with selectedDate.takeIf {
            calendar.month != selectedDate.month
        } ?: today.takeIf {
            firstDay.month == today.month && firstDay.year == today.year
        } ?: firstDay
    }

    fun onSmallCalendarPageChange(page: Int) {
        if(page == current.currentWeekIndex) return
        val newSelectDate = current.weekList[page].first()
        onDateSelect(newSelectDate)
    }

    fun onListPageChange(page: Int) {
        val prevPage = current.currentListPage
        if(page == prevPage) return
        val newSelectDate = Date(
            localDate = with(current.selectedDate.localDate) {
                if(page > prevPage) plusDays(1) else minusDays(1)
            }
        )
        onDateSelect(newSelectDate)
    }

    private fun getWeekIndex(date: Date): Int {
        return current.weekList.indexOfFirst { it.contains(date) }
    }

    fun onDateSelect(date: Date) {
        if(date == current.selectedDate) return

        // 2025년 8월 31일만 특별하게 9월로 처리
        val d20250831 = date == Date(localDate = LocalDate.of(2025, 8, 31))
        _calendarUiState.update {
            it.copy(
                calendar = DateUtils.createCalendar(date.year, if(d20250831) 9 else date.month),
                selectedDate = date,
                currentCalendarPage = if (d20250831) 0 else calculateNextCalendarPage(date),
                currentWeekIndex = getWeekIndex(date),
                currentListPage = DateUtils.getDateDiff(date.localDate),
            )
        }
    }

    private fun calculateNextCalendarPage(date: Date): Int = with(current) {
        if(date == today) return@with initialCalendarPage

        val currentMonth = calendar.month
        val selectedMonth = date.month
        val currentPage = currentCalendarPage

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
