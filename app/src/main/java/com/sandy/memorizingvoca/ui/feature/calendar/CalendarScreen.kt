package com.sandy.memorizingvoca.ui.feature.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sandy.memorizingvoca.ui.feature.calendar.components.CalendarHeader
import com.sandy.memorizingvoca.ui.feature.calendar.components.CalendarTopBar
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme

@Composable
internal fun CalendarRoute(
    navigateQuizResult: (String) -> Unit,
) {
    CalendarScreen(
        year = 2005,
        month = 9,
        dayOfWeeks = DayOfWeek.list(),
        onAllQuizClear = {},
    )
}

@Composable
private fun CalendarScreen(
    year: Int,
    month: Int,
    dayOfWeeks: List<DayOfWeek>,
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
            onAllQuizClear = {},
        )
    }
}
