package com.sandy.memorizingvoca.ui.feature.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sandy.memorizingvoca.data.model.Calendar
import com.sandy.memorizingvoca.data.model.Date
import com.sandy.memorizingvoca.data.model.DayOfWeek
import com.sandy.memorizingvoca.ui.feature.calendar.components.CalendarHeader
import com.sandy.memorizingvoca.ui.feature.calendar.components.CalendarPagerView
import com.sandy.memorizingvoca.ui.feature.calendar.components.CalendarTopBar
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme
import com.sandy.memorizingvoca.utils.DateUtils

@Composable
internal fun CalendarRoute(
    navigateQuizResult: (String) -> Unit,
    viewModel: CalendarViewModel = hiltViewModel(),
) {

    val uiState by viewModel.calendarUiState.collectAsStateWithLifecycle()

    CalendarScreen(
        calendar = uiState.calendar,
        calendarList = uiState.calendarList,
        dayOfWeeks = uiState.dayOfWeeks,
        selectDate = uiState.selectedDate,
        today = uiState.today,
        onPageChange = viewModel::onPageChange,
        onDateSelect = { _, _ -> },
        onAllQuizClear = {},
    )
}

@Composable
private fun CalendarScreen(
    calendar: Calendar,
    calendarList: List<Calendar>,
    dayOfWeeks: List<DayOfWeek>,
    selectDate: Date,
    today: Date,
    onPageChange: (Int) -> Unit,
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
            year = calendar.year,
            month = calendar.month,
            dayOfWeeks = dayOfWeeks,
        )
        CalendarPagerView(
            selectDate = selectDate,
            calendarList = calendarList,
            initialCalendarPage = 0,
            month = calendar.month,
            today = today,
            onPageChange = onPageChange,
            onDateSelect = onDateSelect,
        )
    }
}

@Preview
@Composable
private fun CalendarScreenPreview() {
    val date = Date()
    MemorizingVocaTheme {
        CalendarScreen(
            calendar = DateUtils.createCalendar(date.year, date.month),
            calendarList = DateUtils.createCalendarList(),
            dayOfWeeks = DayOfWeek.list(),
            selectDate = date,
            today = date,
            onPageChange = {},
            onDateSelect = { _, _ -> },
            onAllQuizClear = {},
        )
    }
}
