package com.sandy.memorizingvoca.ui.feature.calendar.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sandy.memorizingvoca.ui.common.MyTextButton
import com.sandy.memorizingvoca.ui.theme.Gray30
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme

@Composable
internal fun CalendarQuizListHeader(
    year: Int,
    month: Int,
    day: Int,
    dayOfWeekName: String,
    onDeleteListClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(start = 16.dp)
        ) {
            Text(
                modifier = modifier.align(Alignment.CenterStart),
                text = "${year}년 ${month}월 ${day}일 $dayOfWeekName",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
            )
            MyTextButton(
                modifier = modifier.align(Alignment.CenterEnd),
                title = "전체삭제",
                onClick = onDeleteListClick,
            )
            Spacer(modifier = modifier.width(16.dp),)
        }
        HorizontalDivider(color = MaterialTheme.colorScheme.outline)
    }
}

@Preview
@Composable
private fun CalendarQuizListHeaderPreview() {
    MemorizingVocaTheme {
        CalendarQuizListHeader(
            year = 2025,
            month = 9,
            day = 24,
            dayOfWeekName = "수요일",
            onDeleteListClick = { },
        )
    }
}
