package com.sandy.memorizingvoca.ui.feature.quiz_result.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sandy.memorizingvoca.ui.theme.Gray30
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme
import com.sandy.memorizingvoca.ui.theme.Pink100
import com.sandy.memorizingvoca.ui.theme.roundedCornerShape16

@Composable
internal fun QuizResultListHeaderView(
    incorrectCount: Int?,
    onAllBookmarkClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "틀린 단어(",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
            )
            Text(
                text = "${incorrectCount ?: 0}",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Pink100,
            )
            Text(
                text = ")",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
            )
            Spacer(modifier = modifier.weight(1f))
            Box(
                modifier = modifier
                    .background(
                        color = Color.White,
                        shape = roundedCornerShape16,
                    )
                    .border(
                        width = 1.dp,
                        color = Color.LightGray,
                        shape = roundedCornerShape16,
                    )
                    .clip(roundedCornerShape16)
                    .clickable(onClick = onAllBookmarkClick)
                    .padding(horizontal = 16.dp, vertical = 6.dp),
            ) {
                Text(
                    text = "전체북마크",
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Medium,
                )
            }
        }
        HorizontalDivider(color = Gray30)
    }
}

@Preview
@Composable
private fun QuizResultListHeaderViewPreview() {
    MemorizingVocaTheme {
        QuizResultListHeaderView(
            incorrectCount = 0,
            onAllBookmarkClick = {},
        )
    }
}
