package com.sandy.memorizingvoca.ui.feature.calendar


internal data class CalendarUiState(
    val today: Date = Date(),
    val selectedDate: Date = Date(),
    val year: Int = today.year,
    val month: Int = today.month,
    val calendar: List<Week> = DateUtils.createCalendar(year, month),
    val type: CalendarType = CalendarType.NORMAL_CALENDAR,
    val scrollState: CalendarScroll = CalendarScroll.NONE,
    val dayOfWeeks: List<DayOfWeek> = DayOfWeek.list(),
)

internal enum class CalendarType {
    SMALL_CALENDAR,
    NORMAL_CALENDAR,
    EXPANDED_CALENDAR,
}

internal enum class CalendarScroll {
    UP_SCROLL,
    DOWN_SCROLL,
    NONE,
}
