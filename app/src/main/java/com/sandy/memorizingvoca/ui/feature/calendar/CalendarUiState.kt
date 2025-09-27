package com.sandy.memorizingvoca.ui.feature.calendar


internal data class CalendarUiState(
    val today: Date = Date(),
    val selectedDate: Date = Date(),
    val year: Int = today.year,
    val month: Int = today.month,
    val calendar: List<List<Date>> = DateUtils.createCalendar(year, month),
    val dayOfWeeks: List<DayOfWeek> = DayOfWeek.list(),
)
