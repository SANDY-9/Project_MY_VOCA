package com.sandy.memorizingvoca.ui.feature.calendar

import com.sandy.memorizingvoca.data.model.Calendar
import com.sandy.memorizingvoca.data.model.Date
import com.sandy.memorizingvoca.data.model.DayOfWeek
import com.sandy.memorizingvoca.data.model.VocaQuiz
import com.sandy.memorizingvoca.utils.DateUtils


internal data class CalendarUiState(
    val today: Date = Date(),
    val calendarList: List<Calendar> = DateUtils.createCalendarList(),
    val calendar: Calendar = DateUtils.createCalendar(today.year, today.month),
    val quizCalendar: Map<Date, List<VocaQuiz>> = emptyMap(),
    val quizList: List<VocaQuiz> = emptyList(),
    val initialCalendarPage: Int = calendarList.indexOf(calendar),
    val currentCalendarPage: Int = initialCalendarPage,
    val selectedDate: Date = today,
    val dayOfWeeks: List<DayOfWeek> = DayOfWeek.list(),
)
