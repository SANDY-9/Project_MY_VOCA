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
            val newCalendar = calendarList[page]
            val firstDay = DateUtils.getFirstDay(newCalendar.year, newCalendar.month).let {
                Date(localDate = it,)
            }
            val newSelectedDate = today.takeIf {
                firstDay.month == today.month && firstDay.year == today.year
            } ?: firstDay
            copy(
                calendar = newCalendar,
                currentCalendarPage = page,
                selectedDate = newSelectedDate,
                quizList = quizCalendar[newSelectedDate] ?: emptyList(),
                currentListPage = allDateList[newSelectedDate] ?: currentListPage,
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
        onDateSelect(newSelectDate)
    }

    fun onDateSelect(date: Date) {
        _calendarUiState.update {
            it.copy(
                selectedDate = date,
                quizList = it.quizCalendar[date] ?: emptyList(),
                currentListPage = current.allDateList[date] ?: current.currentListPage,
            )
        }
    }

}