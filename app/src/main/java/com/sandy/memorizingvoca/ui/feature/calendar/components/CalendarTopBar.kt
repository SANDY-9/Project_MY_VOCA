package com.sandy.memorizingvoca.ui.feature.calendar.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme
import com.sandy.memorizingvoca.ui.theme.Pink100
import com.sandy.memorizingvoca.ui.theme.PyeoginGothic

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
        TextButton(
            onClick = onAllQuizClearClick,
        ) {
            Text(
                text = "전체삭제",
                fontFamily = PyeoginGothic,
                color = Pink100,
            )
        }
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
