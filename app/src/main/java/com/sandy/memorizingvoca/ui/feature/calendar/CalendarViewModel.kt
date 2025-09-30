package com.sandy.memorizingvoca.ui.feature.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandy.memorizingvoca.data.model.Calendar
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

    fun onCalendarPageChange(page: Int) {
        if(page == current.currentCalendarPage) return
        _calendarUiState.value = with(current) {
            val newCalendar = allCalendarList[page]
            val newSelectedDate = getNewSelectedDate(newCalendar)
            copy(
                calendar = newCalendar,
                currentCalendarPage = page,
                selectedDate = newSelectedDate,
                currentWeekIndex = getWeekIndex(newSelectedDate),
                quizList = quizCalendar[newSelectedDate] ?: emptyList(),
            )
        }
    }

    private fun getNewSelectedDate(newCalendar: Calendar) = with(current) {
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
        _calendarUiState.value = with(current) {
            val newSelectDate = weekList[page].first()
            val newCalendar = DateUtils.createCalendar(newSelectDate.year, newSelectDate.month)
            copy(
                calendar = DateUtils.createCalendar(newSelectDate.year, newSelectDate.month),
                currentCalendarPage = allCalendarList.indexOf(newCalendar),
                selectedDate = newSelectDate,
                currentWeekIndex = getWeekIndex(newSelectDate),
                quizList = quizCalendar[newSelectDate] ?: emptyList(),
            )
        }
    }

    fun onListPageChange(page: Int) = current.apply {
        if(page == currentListPage) return@apply
        val newSelectDate = Date(
            localDate = with(selectedDate.localDate) {
                if(page > currentListPage) plusDays(1) else minusDays(1)
            }
        )
        _calendarUiState.value = copy(
            currentListPage = page,
            selectedDate = newSelectDate,
            quizList = quizCalendar[newSelectDate] ?: emptyList(),
            currentWeekIndex = getWeekIndex(newSelectDate),
        )
        onDateSelect(newSelectDate)
    }

    private fun getWeekIndex(date: Date): Int {
        return current.weekList.indexOfFirst { it.contains(date) }
    }

    fun onDateSelect(date: Date) {
        _calendarUiState.update {
            it.copy(
                calendar = DateUtils.createCalendar(date.year, date.month),
                selectedDate = date,
                currentCalendarPage = calculateNextCalendarPage(date),
                currentWeekIndex = getWeekIndex(date),
                quizList = it.quizCalendar[date] ?: emptyList(),
                currentListPage = current.allDateList[date] ?: current.currentListPage,
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