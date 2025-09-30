package com.sandy.memorizingvoca.ui.feature.calendar

import com.sandy.memorizingvoca.data.model.Calendar
import com.sandy.memorizingvoca.data.model.Date
import com.sandy.memorizingvoca.data.model.DayOfWeek
import com.sandy.memorizingvoca.data.model.VocaQuiz
import com.sandy.memorizingvoca.utils.DateUtils


internal data class CalendarUiState(
    val today: Date = Date(), // 초기값 후 불변
    val selectedDate: Date = today,

    val calendarType: CalendarType = CalendarType.EXPANDED_CALENDAR,

    val allCalendarList: List<Calendar> = DateUtils.createCalendarList(), // 초기값 후 불변
    val calendar: Calendar = DateUtils.createCalendar(today.year, today.month),
    val initialCalendarPage: Int = allCalendarList.indexOf(calendar), // 초기값 후 불변
    val currentCalendarPage: Int = initialCalendarPage,

    val weekList: List<List<Date>> = DateUtils.createWeekList(), // 초기값 후 불변
    val initialWeekIndex: Int = weekList.indexOfFirst { it.contains(selectedDate) },
    val currentWeekIndex: Int = initialWeekIndex,

    val quizCalendar: Map<Date, List<VocaQuiz>> = emptyMap(),
    val quizList: List<VocaQuiz> = emptyList(),

    val allDateList: Map<Date, Int> = DateUtils.createDateMap(),
    val listPageSize: Int = allDateList.size, // 초기값 후 불변
    val initialListPage: Int = allDateList[today] ?: 0, // 초기값 후 불변
    val currentListPage: Int = initialListPage,

    val dayOfWeeks: List<DayOfWeek> = DayOfWeek.list(), // 초기값 후 불변
)

