package com.sandy.memorizingvoca.ui.feature.calendar.components.calendar

import SmallCalendar
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sandy.memorizingvoca.data.model.Calendar
import com.sandy.memorizingvoca.data.model.Date
import com.sandy.memorizingvoca.data.model.VocaQuiz
import com.sandy.memorizingvoca.ui.feature.calendar.CalendarType
import com.sandy.memorizingvoca.ui.feature.calendar.FlexibleCalendarState
import com.sandy.memorizingvoca.ui.feature.calendar.rememberFlexibleCalendarState
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme
import com.sandy.memorizingvoca.utils.DateUtils
import java.time.LocalDate
import java.time.LocalDateTime

@Composable
internal fun FlexibleCalendar(
    page: Int,
    month: Int,
    today: Date,
    selectDate: Date,
    quizCalendar: Map<Date, List<VocaQuiz>>,
    calendarList: List<Calendar>,
    quizList: List<List<VocaQuiz>>,
    weekList: List<List<Date>>,
    weekIndex: Int,
    onQuizItemClick: (String) -> Unit,
    onDateSelect: (Date) -> Unit,
    modifier: Modifier = Modifier,
    flexibleCalendarState: FlexibleCalendarState = rememberFlexibleCalendarState(),
) {
    var quizItem by remember {
        mutableStateOf(
            quizList.chunked(7)[weekIndex]
        )
    }
    LaunchedEffect(quizList, weekIndex) {
        quizItem = quizList.chunked(7)[weekIndex]
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .animateContentSize()
            .fillMaxHeight(flexibleCalendarState.fraction)
    ) {
        when(flexibleCalendarState.type) {
            CalendarType.EXPANDED_CALENDAR -> ExpandCalendar(
                today = today,
                selectDate = selectDate,
                calendar = calendarList[page],
                quizCalendar = quizCalendar,
                onQuizItemClick = onQuizItemClick,
                onDateSelect = onDateSelect,
            )
            CalendarType.NORMAL_CALENDAR -> NormalCalendar(
                selectDate = selectDate,
                calendar = calendarList[page],
                quizCalendar = quizCalendar,
                today = today,
                onDateSelect = onDateSelect,
            )
            CalendarType.SMALL_CALENDAR -> SmallCalendar(
                selectDate = selectDate,
                month = month,
                today = today,
                weekList = weekList[weekIndex],
                quizList = quizItem,
                onDateSelect = onDateSelect,
            )
        }
    }
}

@Preview
@Composable
private fun FlexibleCalendarPreview() {
    val date = Date()
    MemorizingVocaTheme {
        FlexibleCalendar(
            page = 0,
            month = 9,
            selectDate = date,
            calendarList = DateUtils.createCalendarList(),
            today = Date(),
            quizCalendar = emptyMap(),
            quizList = emptyList(),
            weekList = emptyList(),
            weekIndex = 0,
            onQuizItemClick = {},
            onDateSelect = {},
        )
    }
}
