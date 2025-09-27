package com.sandy.memorizingvoca.ui.feature.calendar.components.calendar

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sandy.memorizingvoca.ui.feature.calendar.Date
import com.sandy.memorizingvoca.ui.feature.calendar.DateUtils
import com.sandy.memorizingvoca.ui.theme.Gray30
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme

@Composable
internal fun ExpandCalendar(
    calendar: List<List<Date>>,
    month: Int,
    today: Date,
    selectDate: Date?,
    onDateSelect: (Date, Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column (
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        calendar.forEachIndexed { week, days ->
            Row(modifier = modifier.weight(1f),) {
                days.forEach { date ->
                    Column(
                        modifier = modifier.weight(1f),
                    ) {
                        DateHeader(
                            date = date,
                            isToday = date == today,
                            otherMonth = month != date.month,
                        )
                        Spacer(modifier = modifier.weight(1f))
                    }
                }
            }
            if(week != calendar.lastIndex) {
                HorizontalDivider(color = Gray30)
            }
        }
    }
}

@Preview
@Composable
private fun ExpandCalendarPreview() {
    MemorizingVocaTheme {
        val date = Date()
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            ExpandCalendar(
                calendar = DateUtils.createCalendar(
                    year = date.year,
                    month = date.month,
                ),
                month = date.month,
                today = Date(),
                selectDate = null,
                onDateSelect = { _, _-> },
            )
        }
    }
}
