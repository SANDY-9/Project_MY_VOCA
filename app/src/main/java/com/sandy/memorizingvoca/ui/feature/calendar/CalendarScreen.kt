package com.sandy.memorizingvoca.ui.feature.calendar

import androidx.activity.compose.BackHandler
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
import com.sandy.memorizingvoca.data.model.VocaQuiz
import com.sandy.memorizingvoca.ui.extensions.clickEffect
import com.sandy.memorizingvoca.ui.feature.calendar.components.CalendarHeader
import com.sandy.memorizingvoca.ui.feature.calendar.components.CalendarPagerView
import com.sandy.memorizingvoca.ui.feature.calendar.components.CalendarTopBar
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme
import com.sandy.memorizingvoca.utils.DateUtils
import java.time.LocalDate
import java.time.LocalDateTime

@Composable
internal fun CalendarRoute(
    onNavigateQuizResult: (String) -> Unit,
    onNavigateBack: () -> Unit,
    viewModel: CalendarViewModel = hiltViewModel(),
) {
    BackHandler {
        onNavigateBack()
    }
    val uiState by viewModel.calendarUiState.collectAsStateWithLifecycle()

    CalendarScreen(
        selectDate = uiState.selectedDate,
        today = uiState.today,
        calendarType = uiState.calendarType,
        calendarFraction = uiState.calendarFraction,
        calendar = uiState.calendar,
        quizCalendar = uiState.quizCalendar,
        calendarList = uiState.allCalendarList,
        weekList = uiState.weekList,
        quizList = uiState.quizList,
        initialWeekIndex = uiState.initialWeekIndex,
        currentWeekIndex = uiState.currentWeekIndex,
        dayOfWeeks = uiState.dayOfWeeks,
        initialCalendarPage = uiState.initialCalendarPage,
        currentCalendarPage = uiState.currentCalendarPage,
        initialListPage = uiState.initialListPage,
        currentListPage = uiState.currentListPage,
        onDateSelect = viewModel::onDateSelect,
        onCalendarTypeChange = viewModel::onCalendarTypeChange,
        onCalendarPageChange = viewModel::onCalendarPageChange,
        onSmallCalendarPageChange = viewModel::onSmallCalendarPageChange,
        onListPageChange = viewModel::onListPageChange,
        onQuizItemClick = onNavigateQuizResult,
        onDeleteQuizClick = viewModel::deleteQuiz,
        onDeleteListClick = viewModel::deleteMultipleQuiz,
        onAllQuizClear = viewModel::clearCalendar,
        onCalendarFractionChange = viewModel::onCalendarFractionChange,
    )
}

@Composable
private fun CalendarScreen(
    selectDate: Date,
    today: Date,
    calendar: Calendar,
    calendarList: List<Calendar>,
    quizCalendar: Map<Date, List<VocaQuiz>>,
    weekList: List<List<Date>>,
    quizList: List<List<VocaQuiz>>,
    initialWeekIndex: Int,
    currentWeekIndex: Int,
    initialCalendarPage: Int,
    currentCalendarPage: Int,
    initialListPage: Int,
    currentListPage: Int,
    onDateSelect: (Date) -> Unit,
    onCalendarPageChange: (Int) -> Unit,
    onSmallCalendarPageChange: (Int) -> Unit,
    onListPageChange: (Int) -> Unit,
    onQuizItemClick: (String) -> Unit,
    onDeleteQuizClick: (VocaQuiz) -> Unit,
    onDeleteListClick: () -> Unit,
    onAllQuizClear: () -> Unit,
    dayOfWeeks: List<DayOfWeek>,
    calendarFraction: Float,
    calendarType: CalendarType,
    onCalendarTypeChange: (CalendarType) -> Unit,
    onCalendarFractionChange: (Float) -> Unit,
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
            onTodayButtonClick = clickEffect {
                onDateSelect(today)
            },
        )
        CalendarPagerView(
            today = today,
            selectDate = selectDate,
            calendar = calendar,
            calendarList = calendarList,
            quizCalendar = quizCalendar,
            quizList = quizList,
            weekList = weekList,
            initialWeekIndex = initialWeekIndex,
            currentWeekIndex = currentWeekIndex,
            initialCalendarPage = initialCalendarPage,
            currentCalendarPage = currentCalendarPage,
            initialListPage = initialListPage,
            currentListPage = currentListPage,
            onDateSelect = onDateSelect,
            onCalendarPageChange = onCalendarPageChange,
            onSmallCalendarPageChange = onSmallCalendarPageChange,
            onListPageChange = onListPageChange,
            onQuizItemClick = onQuizItemClick,
            onDeleteListClick = onDeleteListClick,
            onDeleteQuizClick = onDeleteQuizClick,
            calendarType = calendarType,
            calendarFraction = calendarFraction,
            onCalendarTypeChange = onCalendarTypeChange,
            onCalendarFractionChange = onCalendarFractionChange,
        )
    }
}

@Preview
@Composable
private fun CalendarScreenPreview() {
    val date = Date()
    MemorizingVocaTheme {
        CalendarScreen(
            calendarType = CalendarType.EXPANDED_CALENDAR,
            calendarFraction = 1f,
            calendar = DateUtils.createCalendar(date.year, date.month),
            calendarList = DateUtils.createCalendarList(),
            dayOfWeeks = DayOfWeek.list(),
            selectDate = date,
            today = date,
            quizCalendar = mapOf(
                date to listOf(
                    VocaQuiz(
                        date = LocalDateTime.now().toString(),
                        day = 0,
                        wrongCount = 0,
                        totalCount = 10,
                    ),
                    VocaQuiz(
                        date = LocalDateTime.now().toString(),
                        day = 2,
                        wrongCount = 0,
                        totalCount = 10,
                    ),
                    VocaQuiz(
                        date = LocalDateTime.now().toString(),
                        day = 0,
                        wrongCount = 0,
                        totalCount = 10,
                    ),
                ),
            ),
            quizList = listOf(
                listOf(
                    VocaQuiz(
                        date = LocalDateTime.now().toString(),
                        day = 0,
                        wrongCount = 0,
                        totalCount = 10,
                    ),
                    VocaQuiz(
                        date = LocalDateTime.now().toString(),
                        day = 2,
                        wrongCount = 0,
                        totalCount = 10,
                    ),
                    VocaQuiz(
                        date = LocalDateTime.now().toString(),
                        day = 0,
                        wrongCount = 0,
                        totalCount = 10,
                    ),
                ),
            ),
            weekList = listOf(
                listOf(
                    Date(
                        localDate = LocalDate.now().minusDays(1),
                    ),
                    Date(),
                    Date(
                        localDate = LocalDate.now().plusDays(1),
                    ),
                    Date(
                        localDate = LocalDate.now().plusDays(2),
                    ),
                    Date(
                        localDate = LocalDate.now().plusDays(3),
                    ),
                    Date(
                        localDate = LocalDate.now().plusDays(4),
                    ),
                    Date(
                        localDate = LocalDate.now().plusDays(5),
                    ),
                )
            ),
            initialWeekIndex = 0,
            currentWeekIndex = 0,
            initialCalendarPage = 0,
            currentCalendarPage = 0,
            initialListPage = 3,
            currentListPage = 1,
            onDateSelect = {},
            onCalendarTypeChange = {},
            onCalendarPageChange = {},
            onSmallCalendarPageChange = {},
            onListPageChange = {},
            onQuizItemClick = {},
            onAllQuizClear = {},
            onDeleteQuizClick = {},
            onDeleteListClick = {},
            onCalendarFractionChange = {},
        )
    }
}
