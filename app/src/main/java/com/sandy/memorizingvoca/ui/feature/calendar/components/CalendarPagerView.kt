package com.sandy.memorizingvoca.ui.feature.calendar.components

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
    month: Int,
    today: Date,
    onPageChange: (Int) -> Unit,
    onQuizItemClick: (String) -> Unit,
    onDateSelect: (Date, Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val flexibleCalendarState = rememberFlexibleCalendarState()
    val pagerState = rememberPagerState(
        initialPage = initialCalendarPage,
        pageCount = { calendarList.size }
    )
    LaunchedEffect(pagerState) {
        snapshotFlow {
            pagerState.currentPage
        }
            .distinctUntilChanged()
            .collectLatest { page ->
                onPageChange(page)
            }
    }

    HorizontalPager(
        modifier = modifier,
        state = pagerState,
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
            onPageChange = {},
            onQuizItemClick = {},
            onDateSelect = { _, _ -> },
        )
    }
}
