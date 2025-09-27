package com.sandy.memorizingvoca.ui.feature.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sandy.memorizingvoca.ui.feature.calendar.components.CalendarHeader
import com.sandy.memorizingvoca.ui.feature.calendar.components.CalendarPagerView
import com.sandy.memorizingvoca.ui.feature.calendar.components.CalendarTopBar
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme

private val date = Date()
private val calendar = DateUtils.createCalendar(date.year, date.month)

@Composable
internal fun CalendarRoute(
    navigateQuizResult: (String) -> Unit,
) {
    CalendarScreen(
        year = 2005,
        month = 9,
        calendar = calendar,
        dayOfWeeks = DayOfWeek.list(),
        selectDate = null,
        today = Date(),
        onDateSelect = { _, _ -> },
        onAllQuizClear = {},
    )
}

@Composable
private fun CalendarScreen(
    year: Int,
    month: Int,
    calendar: List<List<Date>>,
    dayOfWeeks: List<DayOfWeek>,
    selectDate: Date?,
    today: Date,
    onDateSelect: (Date, Int) -> Unit,
    onAllQuizClear: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        CalendarTopBar(
            onAllQuizClearClick = onAllQuizClear,
        )
        CalendarHeader(
            year = year,
            month = month,
            dayOfWeeks = dayOfWeeks,
        )
        CalendarPagerView(
            selectDate = selectDate,
            calendar = calendar,
            month = month,
            today = today,
            onDateSelect = onDateSelect,
        )
    }
}

@Preview
@Composable
private fun CalendarScreenPreview() {
    MemorizingVocaTheme {
        CalendarScreen(
            year = 2005,
            month = 9,
            dayOfWeeks = DayOfWeek.list(),
            calendar = calendar,
            selectDate = null,
            today = Date(),
            onDateSelect = { _, _ -> },
            onAllQuizClear = {},
        )
    }
}
