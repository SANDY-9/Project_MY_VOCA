package com.sandy.memorizingvoca.ui.feature.calendar.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sandy.memorizingvoca.data.model.DayOfWeek
import com.sandy.memorizingvoca.ui.theme.Gray30
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme
import com.sandy.memorizingvoca.ui.theme.Pink10

@Composable
internal fun CalendarHeader(
    year: Int,
    month: Int,
    dayOfWeeks: List<DayOfWeek>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "${year}년 ${month}월",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
        )
        Spacer(modifier = modifier.height(12.dp))
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    color = Pink10,
                )
                .padding(
                    horizontal = 8.dp,
                    vertical = 4.dp,
                ),
        ) {
            dayOfWeeks.forEach { dayOfWeek ->
                Text(
                    modifier = modifier.weight(1f),
                    text = dayOfWeek.desc,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium,
                    color = dayOfWeek.color,
                )
            }
        }
        HorizontalDivider(
            modifier = modifier.padding(horizontal = 8.dp),
            color = Gray30,
        )
    }
}


@Preview
@Composable
private fun CalendarHeaderPreview() {
    MemorizingVocaTheme {
        CalendarHeader(
            year = 2025,
            month = 9,
            dayOfWeeks = DayOfWeek.list(),
        )
    }
}
