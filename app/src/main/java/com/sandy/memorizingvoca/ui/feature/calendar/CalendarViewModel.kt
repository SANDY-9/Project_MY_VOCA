package com.sandy.memorizingvoca.ui.feature.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandy.memorizingvoca.data.model.VocaQuiz
import com.sandy.memorizingvoca.utils.DateUtils
import com.sandy.memorizingvoca.data.repository.GetQuizRepository
import com.sandy.memorizingvoca.data.repository.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@Suppress("OPT_IN_USAGE")
@HiltViewModel
internal class CalendarViewModel @Inject constructor(
    private val getQuizRepository: GetQuizRepository,
    private val quizRepository: QuizRepository,
): ViewModel() {

    private val _calendarUiState = MutableStateFlow(CalendarUiState())
    val calendarUiState = _calendarUiState.asStateFlow()

    init {
        calendarUiState.map {
            it.yearMonth
        }.distinctUntilChanged()
            .flatMapMerge { yearMonth ->
                val startDay = DateUtils.getStartOfMonth(yearMonth.year, yearMonth.month)
                val endDate = DateUtils.getEndOfMonth(yearMonth.year, yearMonth.month)
                getQuizRepository.getQuizListForCalendar(startDay, endDate)
            }.onEach { quizCalendar ->
                _calendarUiState.update {
                    it.copy(
                        quizCalendar = quizCalendar,
                    )
                }
            }.launchIn(viewModelScope)

        calendarUiState.map {
            it.selectedDate
        }.distinctUntilChanged()
            .flatMapMerge { date ->
                getQuizRepository.getQuizListForDate(date.localDate)
            }.onEach { quizList ->
                _calendarUiState.update {
                    it.copy(
                        quizList = quizList,
                    )
                }
            }.launchIn(viewModelScope)
    }


}