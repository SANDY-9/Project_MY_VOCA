package com.sandy.memorizingvoca.ui.feature.calendar.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sandy.memorizingvoca.data.model.Calendar
import com.sandy.memorizingvoca.data.model.Date
import com.sandy.memorizingvoca.data.model.VocaQuiz
import com.sandy.memorizingvoca.ui.feature.calendar.CalendarType
import com.sandy.memorizingvoca.ui.feature.calendar.components.calendar.FlexibleCalendar
import com.sandy.memorizingvoca.ui.feature.calendar.rememberFlexibleCalendarState
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme
import com.sandy.memorizingvoca.utils.DateUtils
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import java.time.LocalDate
import java.time.LocalDateTime

@Composable
internal fun CalendarPagerView(
    calendarType: CalendarType,
    today: Date,
    calendar: Calendar,
    selectDate: Date,
    calendarList: List<Calendar>,
    weekList: List<List<Date>>,
    initialWeekIndex: Int,
    currentWeekIndex: Int,
    quizCalendar: Map<Date, List<VocaQuiz>>,
    initialCalendarPage: Int,
    currentCalendarPage: Int,
    initialListPage: Int,
    currentListPage: Int,
    dateSize: Int,
    onCalendarTypeChange: (CalendarType) -> Unit,
    onCalendarPageChange: (Int) -> Unit,
    onSmallCalendarPageChange: (Int) -> Unit,
    onListPageChange: (Int) -> Unit,
    onQuizItemClick: (String) -> Unit,
    onDateSelect: (Date) -> Unit,
    modifier: Modifier = Modifier,
) {
    val flexibleCalendarState = rememberFlexibleCalendarState(calendarType)
    LaunchedEffect(flexibleCalendarState) {
        onCalendarTypeChange(flexibleCalendarState.type)
    }

    val calendarPagerState = rememberPagerState(
        initialPage = initialCalendarPage,
        pageCount = { calendarList.size }
    )
    LaunchedEffect(calendarPagerState) {
        snapshotFlow {
            calendarPagerState.currentPage
        }
            .distinctUntilChanged()
            .collectLatest { page ->
                onCalendarPageChange(page)
            }
    }
    LaunchedEffect(currentCalendarPage) {
        if(currentCalendarPage != calendarPagerState.currentPage) {
            calendarPagerState.animateScrollToPage(currentCalendarPage)
        }
    }

    val smallCalendarPagerState = rememberPagerState(
        initialPage = initialWeekIndex,
        pageCount = { weekList.size }
    )
    LaunchedEffect(smallCalendarPagerState) {
        snapshotFlow {
            smallCalendarPagerState.currentPage
        }
            .distinctUntilChanged()
            .collectLatest { page ->
                onSmallCalendarPageChange(page)
            }
    }
    LaunchedEffect(currentWeekIndex) {
        if(currentWeekIndex != smallCalendarPagerState.currentPage) {
            smallCalendarPagerState.scrollToPage(currentWeekIndex)
        }
    }


    val listPagerState = rememberPagerState(
        initialPage = initialListPage,
        pageCount = { dateSize }
    )
    LaunchedEffect(listPagerState) {
        snapshotFlow {
            listPagerState.currentPage
        }
            .distinctUntilChanged()
            .collectLatest { page ->
                onListPageChange(page)
            }
    }
    LaunchedEffect(currentListPage) {
        if(currentListPage != listPagerState.currentPage) {
            listPagerState.scrollToPage(currentListPage)
        }
    }

    val isSmallCalendar = flexibleCalendarState.type == CalendarType.SMALL_CALENDAR
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        HorizontalPager(
            state = if(isSmallCalendar) smallCalendarPagerState else calendarPagerState,
        ) { page ->
            FlexibleCalendar(
                page = page,
                month = calendar.month,
                today = today,
                calendarState = flexibleCalendarState,
                selectDate = selectDate,
                calendarList = calendarList,
                weekList = weekList,
                weekIndex = if(isSmallCalendar) page else currentWeekIndex,
                quizCalendar = quizCalendar,
                onQuizItemClick = onQuizItemClick,
                onDateSelect = onDateSelect,
            )
        }
        HorizontalPager(
            modifier = modifier.weight(1f),
            state = listPagerState,
        ) {
            if(flexibleCalendarState.type != CalendarType.EXPANDED_CALENDAR) {
                CalendarQuizListView(
                    date = selectDate,
                    quizList = quizCalendar[selectDate] ?: emptyList(),
                    onDeleteListClick = {
                    },
                    onItemClick = onQuizItemClick,
                    onDeleteClick = {},
                )
            }
        }
    }
}

@Preview
@Composable
private fun CalendarPagerViewPreview() {
    val date = Date()
    MemorizingVocaTheme {
        CalendarPagerView(
            calendarType = CalendarType.NORMAL_CALENDAR,
            selectDate = date,
            calendar = DateUtils.createCalendar(date.year, date.month),
            calendarList = DateUtils.createCalendarList(),
            initialCalendarPage = 0,
            currentCalendarPage = 0,
            today = date,
            initialListPage = 3,
            dateSize = 10,
            currentListPage = 1,
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
            currentWeekIndex = 0,
            initialWeekIndex = 0,
            onCalendarTypeChange = {},
            onCalendarPageChange = {},
            onSmallCalendarPageChange = {},
            onListPageChange = {},
            onQuizItemClick = {},
            onDateSelect = {},
        )
    }
}
