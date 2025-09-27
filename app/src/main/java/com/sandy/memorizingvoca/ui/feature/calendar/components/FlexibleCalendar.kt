package com.sandy.memorizingvoca.ui.feature.calendar.components

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
import com.sandy.memorizingvoca.ui.feature.calendar.CalendarType
import com.sandy.memorizingvoca.ui.feature.calendar.Date
import com.sandy.memorizingvoca.ui.feature.calendar.DateUtils
import com.sandy.memorizingvoca.ui.feature.calendar.FlexibleCalendarState
import com.sandy.memorizingvoca.ui.feature.calendar.components.calendar.ExpandCalendar
import com.sandy.memorizingvoca.ui.feature.calendar.components.calendar.NormalCalendar
import com.sandy.memorizingvoca.ui.feature.calendar.rememberFlexibleCalendarState
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme

@Composable
internal fun FlexibleCalendar(
    selectDate: Date?,
    selectDateWeek: Int,
    calendar: List<List<Date>>,
    month: Int,
    today: Date,
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
                month = month,
                today = today,
                onDateSelect = onDateSelect,
            )
            CalendarType.NORMAL_CALENDAR -> NormalCalendar(
                selectDate = selectDate,
                calendar = calendar,
                month = month,
                today = today,
                onDateSelect = onDateSelect,
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
            calendar = calendar,
            month = date.month,
            today = Date(),
            selectDateWeek = date.week,
            onDateSelect = { _, _ -> },
        )
    }
}
