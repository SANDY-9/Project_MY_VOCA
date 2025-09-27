package com.sandy.memorizingvoca.ui.feature.calendar.components.calendar

import SmallCalendar
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sandy.memorizingvoca.data.model.Date
import com.sandy.memorizingvoca.data.model.VocaQuiz
import com.sandy.memorizingvoca.ui.feature.calendar.CalendarType
import com.sandy.memorizingvoca.ui.feature.calendar.FlexibleCalendarState
import com.sandy.memorizingvoca.ui.feature.calendar.rememberFlexibleCalendarState
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme
import com.sandy.memorizingvoca.utils.DateUtils
import java.time.LocalDateTime

@Composable
internal fun FlexibleCalendar(
    selectDate: Date?,
    selectDateWeek: Int,
    calendar: List<List<Date>>,
    quizCalendar: Map<Date, List<VocaQuiz>>,
    month: Int,
    today: Date,
    onQuizItemClick: (String) -> Unit,
    onDateSelect: (Date, Int) -> Unit,
    modifier: Modifier = Modifier,
    calendarState: FlexibleCalendarState = rememberFlexibleCalendarState(),
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .animateContentSize()
            .fillMaxHeight(calendarState.fraction)
            .pointerInput(Unit) {
                detectVerticalDragGestures(
                    onVerticalDrag = calendarState::onVerticalDrag,
                    onDragEnd = calendarState::onDragEnd,
                )
            }
    ) {
        when(calendarState.type) {
            CalendarType.EXPANDED_CALENDAR -> ExpandCalendar(
                selectDate = selectDate,
                calendar = calendar,
                quizCalendar = quizCalendar,
                month = month,
                today = today,
                onQuizItemClick = onQuizItemClick,
                onDateSelect = onDateSelect,
            )
            CalendarType.NORMAL_CALENDAR -> NormalCalendar(
                selectDate = selectDate,
                calendar = calendar,
                month = month,
                today = today,
                onDateSelect = onDateSelect,
                quizCalendar = quizCalendar,
            )
            CalendarType.SMALL_CALENDAR -> SmallCalendar(
                selectDate = selectDate,
                days = calendar[selectDateWeek],
                month = month,
                today = today,
                onDateSelect = onDateSelect,
            )
        }
    }
}

@Preview
@Composable
private fun FlexibleCalendarPreview() {
    val date = Date()
    val calendar = DateUtils.createCalendar(date.year, date.month)
    MemorizingVocaTheme {
        FlexibleCalendar(
            selectDate = date,
            calendar = calendar.days,
            month = date.month,
            today = Date(),
            selectDateWeek = date.weekIndex,
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
            onQuizItemClick = {},
            onDateSelect = { _, _ -> },
        )
    }
}
