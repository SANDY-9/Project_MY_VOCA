package com.sandy.memorizingvoca.ui.feature.calendar.components.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sandy.memorizingvoca.data.model.Date
import com.sandy.memorizingvoca.ui.theme.Gray30
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme
import com.sandy.memorizingvoca.utils.DateUtils

@Composable
internal fun NormalCalendar(
    calendar: List<List<Date>>,
    month: Int,
    today: Date,
    selectDate: Date?,
    onDateSelect: (Date, Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column (
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        calendar.forEachIndexed { weekIndex, days ->
            Row(
                modifier = Modifier.weight(1f)
            ) {
                days.forEach { date ->
                    Column(
                        modifier = Modifier.weight(1f),
                    ) {
                        DateHeader(
                            date = date,
                            isToday = date == today,
                            otherMonth = month != date.month,
                        )
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
            HorizontalDivider(color = Gray30)
        }
    }
}

@Preview
@Composable
private fun NormalCalendarPreview() {
    MemorizingVocaTheme {
        val date = Date()
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            NormalCalendar(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(0.5f),
                calendar = DateUtils.createCalendar(
                    year = date.year,
                    month = date.month,
                ).days,
                month = date.month,
                today = Date(),
                selectDate = null,
                onDateSelect = { _, _-> },
            )
        }
    }
}
