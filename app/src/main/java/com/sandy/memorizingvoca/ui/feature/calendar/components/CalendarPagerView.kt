package com.sandy.memorizingvoca.ui.feature.calendar.components

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sandy.memorizingvoca.data.model.Date
import com.sandy.memorizingvoca.ui.feature.calendar.rememberFlexibleCalendarState
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme
import com.sandy.memorizingvoca.utils.DateUtils

@Composable
internal fun CalendarPagerView(
    selectDate: Date?,
    calendar: List<List<Date>>,
    month: Int,
    today: Date,
    onDateSelect: (Date, Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val flexibleCalendarState = rememberFlexibleCalendarState()
    val pagerState = rememberPagerState { 10 }
    HorizontalPager(
        modifier = modifier,
        state = pagerState,
    ) {
        FlexibleCalendar(
            calendarState = flexibleCalendarState,
            selectDate = selectDate,
            selectDateWeek = selectDate?.week ?: 0,
            calendar = calendar,
            month = month,
            today = today,
            onDateSelect = onDateSelect,
        )
    }
}

@Preview
@Composable
private fun CalendarPagerViewPreview() {
    val date = Date()
    val calendar = DateUtils.createCalendar(date.year, date.month)
    MemorizingVocaTheme {
        CalendarPagerView(
            selectDate = date,
            calendar = calendar,
            month = date.month,
            today = Date(),
            onDateSelect = { _, _ -> },
        )
    }
}
