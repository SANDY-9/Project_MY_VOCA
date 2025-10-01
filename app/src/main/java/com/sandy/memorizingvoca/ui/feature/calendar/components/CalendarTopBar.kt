package com.sandy.memorizingvoca.ui.feature.calendar.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sandy.memorizingvoca.ui.common.MyTextButton
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme

@Composable
internal fun CalendarTopBar(
    onAllQuizClearClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(modifier = modifier.width(16.dp))
        Text(
            text = "퀴즈 캘린더",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
        )
        Spacer(
            modifier = modifier.weight(1f),
        )
        MyTextButton(
            title = "전체삭제",
            onClick = onAllQuizClearClick,
        )
        Spacer(modifier = modifier.width(8.dp),)
    }
}

@Preview
@Composable
private fun CalendarTopBarPreview() {
    MemorizingVocaTheme {
        CalendarTopBar(
            onAllQuizClearClick = {},
        )
    }
}
