package com.sandy.memorizingvoca.ui.feature.calendar

import com.sandy.memorizingvoca.data.model.Date
import com.sandy.memorizingvoca.data.model.DayOfWeek
import com.sandy.memorizingvoca.data.model.VocaQuiz
import com.sandy.memorizingvoca.utils.DateUtils


internal data class CalendarUiState(
    val today: Date = Date(),
    val selectedDate: Date = today,
    val yearMonth: YearMonth = YearMonth(today.year, today.month),
    val calendar: List<List<Date>> = DateUtils.createCalendar(yearMonth.year, yearMonth.month),
    val calendarList: List<Calendar> = DateUtils.createCalendarList(yearMonth.year, yearMonth.month),
    val quizCalendar: Map<Date, List<VocaQuiz>> = emptyMap(),
    val quizList: List<VocaQuiz> = emptyList(),
    val calendarPage: Int = 0,
    val dayOfWeeks: List<DayOfWeek> = DayOfWeek.list(),
)

internal typealias Calendar = List<List<Date>>

internal data class YearMonth(
    val year: Int,
    val month: Int,
)
