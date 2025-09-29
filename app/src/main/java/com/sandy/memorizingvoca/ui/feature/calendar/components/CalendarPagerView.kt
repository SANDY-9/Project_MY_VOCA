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
import java.time.LocalDateTime

@Composable
internal fun CalendarPagerView(
    selectDate: Date,
    calendarList: List<Calendar>,
    quizCalendar: Map<Date, List<VocaQuiz>>,
    initialCalendarPage: Int,
    initialListPage: Int,
    currentListPage: Int,
    dateSize: Int,
    month: Int,
    today: Date,
    onCalendarPageChange: (Int) -> Unit,
    onListPageChange: (Int) -> Unit,
    onQuizItemClick: (String) -> Unit,
    onDateSelect: (Date) -> Unit,
    modifier: Modifier = Modifier,
) {
    val flexibleCalendarState = rememberFlexibleCalendarState()

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

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        HorizontalPager(
            state = calendarPagerState,
        ) { page ->
            val calendar = calendarList[page]
            FlexibleCalendar(
                calendarState = flexibleCalendarState,
                selectDate = selectDate,
                calendar = calendar.days,
                quizCalendar = quizCalendar,
                month = month,
                today = today,
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
                    onItemClick = {},
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
            selectDate = date,
            calendarList = DateUtils.createCalendarList(),
            initialCalendarPage = 0,
            month = date.month,
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
            onCalendarPageChange = {},
            onListPageChange = {},
            onQuizItemClick = {},
            onDateSelect = {},
        )
    }
}
