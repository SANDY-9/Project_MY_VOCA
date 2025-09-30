package com.sandy.memorizingvoca.ui.feature.calendar.components

import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import com.sandy.memorizingvoca.data.model.Calendar
import com.sandy.memorizingvoca.data.model.Date
import com.sandy.memorizingvoca.data.model.VocaQuiz
import com.sandy.memorizingvoca.ui.feature.calendar.CalendarType
import com.sandy.memorizingvoca.ui.feature.calendar.components.calendar.FlexibleCalendar
import com.sandy.memorizingvoca.ui.feature.calendar.rememberFlexibleCalendarState
import com.sandy.memorizingvoca.ui.theme.Gray30
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
    quizCalendar: Map<Date, List<VocaQuiz>>,
    calendarList: List<Calendar>,
    weekList: List<List<Date>>,
    initialWeekIndex: Int,
    currentWeekIndex: Int,
    quizList: List<List<VocaQuiz>>,
    initialCalendarPage: Int,
    currentCalendarPage: Int,
    initialListPage: Int,
    currentListPage: Int,
    onDateSelect: (Date) -> Unit,
    onCalendarTypeChange: (CalendarType) -> Unit,
    onCalendarPageChange: (Int) -> Unit,
    onSmallCalendarPageChange: (Int) -> Unit,
    onListPageChange: (Int) -> Unit,
    onQuizItemClick: (String) -> Unit,
    onDeleteQuizClick: (VocaQuiz) -> Unit,
    onDeleteListClick: () -> Unit,
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
    var isAnimatingCalendar by remember { mutableStateOf(false) }
    LaunchedEffect(calendarPagerState) {
        snapshotFlow { calendarPagerState.currentPage }
            .distinctUntilChanged()
            .collectLatest { page ->
                if (!isAnimatingCalendar) {
                    onCalendarPageChange(page)
                }
            }
    }
    LaunchedEffect(currentCalendarPage) {
        calendarPagerState.run {
            val changePage = currentCalendarPage != currentPage
            if (changePage && !isAnimatingCalendar) {
                val cannotScrolled = !canScrollForward && !canScrollBackward
                if(cannotScrolled) {
                    requestScrollToPage(currentCalendarPage)
                    return@run
                }
                isAnimatingCalendar = true
                animateScrollToPage(currentCalendarPage)
                isAnimatingCalendar = false
            }
        }
    }

    val smallCalendarPagerState = rememberPagerState(
        initialPage = initialWeekIndex,
        pageCount = { weekList.size }
    )
    var isAnimatingSmallCalendar by remember { mutableStateOf(false) }
    LaunchedEffect(smallCalendarPagerState) {
        snapshotFlow { smallCalendarPagerState.currentPage }
            .distinctUntilChanged()
            .collectLatest { page ->
                if(!isAnimatingSmallCalendar) {
                    onSmallCalendarPageChange(page)
                }
            }
    }
    LaunchedEffect(currentWeekIndex) {
        smallCalendarPagerState.run {
            val changeWeek = currentWeekIndex != currentPage
            if(changeWeek && !isAnimatingSmallCalendar) {
                val cannotScrolled = !canScrollForward && !canScrollBackward
                if(cannotScrolled) {
                    requestScrollToPage(currentWeekIndex)
                    return@run
                }
                isAnimatingSmallCalendar = true
                animateScrollToPage(currentWeekIndex)
                isAnimatingSmallCalendar = false
            }
        }
    }

    val listPagerState = rememberPagerState(
        initialPage = initialListPage,
        pageCount = { quizList.size }
    )
    LaunchedEffect(listPagerState) {
        snapshotFlow { listPagerState.currentPage }
            .distinctUntilChanged()
            .collectLatest { page ->
                onListPageChange(page)
            }
    }
    LaunchedEffect(currentListPage) {
        listPagerState.run {
            if(currentListPage != currentPage) {
                val cannotScrolled = !canScrollForward && !canScrollBackward
                if(cannotScrolled) {
                    requestScrollToPage(currentListPage)
                    return@run
                }
                scrollToPage(currentListPage)
            }
        }
    }

    val isSmallCalendar = flexibleCalendarState.type == CalendarType.SMALL_CALENDAR
    Column(
        modifier = modifier.fillMaxSize()
            .pointerInput(Unit) {
                detectVerticalDragGestures(
                    onVerticalDrag = flexibleCalendarState::onVerticalDrag,
                    onDragEnd = flexibleCalendarState::onDragEnd,
                )
            }
    ) {
        HorizontalPager(
            state = if(isSmallCalendar) smallCalendarPagerState else calendarPagerState,
        ) { page ->
            FlexibleCalendar(
                page = page,
                month = calendar.month,
                today = today,
                flexibleCalendarState = flexibleCalendarState,
                selectDate = selectDate,
                calendarList = calendarList,
                quizCalendar = quizCalendar,
                weekList = weekList,
                weekIndex = if(isSmallCalendar) page else currentWeekIndex,
                quizList = quizList,
                onQuizItemClick = onQuizItemClick,
                onDateSelect = onDateSelect,
            )
        }
        if(flexibleCalendarState.type != CalendarType.EXPANDED_CALENDAR) {
            CalendarQuizListHeader(
                year = selectDate.year,
                month = selectDate.month,
                day = selectDate.day,
                dayOfWeekName = selectDate.dayOfWeek.fullName,
                onDeleteListClick = onDeleteListClick,
            )
            HorizontalPager(
                modifier = modifier.weight(1f),
                state = listPagerState,
            ) { page ->
                CalendarQuizListView(
                    quizList = quizList[page],
                    onItemClick = onQuizItemClick,
                    onDeleteItemClick = onDeleteQuizClick,
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
            currentListPage = 1,
            quizCalendar = mapOf(
                Date(
                    localDate = LocalDate.now().plusDays(1)
                ) to listOf(
                    VocaQuiz(
                        date = LocalDateTime.now().toString(),
                        day = 0,
                        wrongCount = 3,
                        totalCount = 10,
                    ),
                    VocaQuiz(
                        date = LocalDateTime.now().toString(),
                        day = 2,
                        wrongCount = 10,
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
            currentWeekIndex = 0,
            initialWeekIndex = 0,
            onCalendarTypeChange = {},
            onCalendarPageChange = {},
            onSmallCalendarPageChange = {},
            onListPageChange = {},
            onQuizItemClick = {},
            onDateSelect = {},
            onDeleteQuizClick = {},
            onDeleteListClick = {},
        )
    }
}
