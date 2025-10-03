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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import com.sandy.memorizingvoca.ui.extensions.clickEffect
import com.sandy.memorizingvoca.ui.extensions.noRippleClickable
import com.sandy.memorizingvoca.ui.resources.Visibility
import com.sandy.memorizingvoca.ui.resources.VisibilityOff
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme
import com.sandy.memorizingvoca.ui.theme.roundedCornerShape16

@Composable
internal fun QuizResultListHeaderView(
    incorrectCount: Int?,
    blindMode: Boolean,
    onBlindModeChange: (Boolean) -> Unit,
    onAllBookmarkClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surface),
    ) {
        Row(
            modifier = modifier.padding(horizontal = 16.dp),
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
                color = MaterialTheme.colorScheme.primary,
            )
            Text(
                text = ")",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
            )
            Spacer(modifier = modifier.width(8.dp))
            Icon(
                modifier = modifier
                    .size(20.dp)
                    .noRippleClickable {
                        onBlindModeChange(!blindMode)
                    },
                imageVector = if (blindMode) Icons.Rounded.VisibilityOff else Icons.Rounded.Visibility,
                contentDescription = null,
                tint = if (blindMode) Color.Gray else MaterialTheme.colorScheme.primary,
            )
            Spacer(modifier = modifier.weight(1f))
            Box(
                modifier = modifier
                    .border(
                        width = 1.dp,
                        color = Color.LightGray,
                        shape = roundedCornerShape16,
                    )
                    .clip(roundedCornerShape16)
                    .clickable(
                        onClick = clickEffect { onAllBookmarkClick() }
                    )
                    .padding(horizontal = 16.dp, vertical = 6.dp),
            ) {
                Text(
                    text = "전체북마크",
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontWeight = FontWeight.Medium,
                )
            }
        }
        Spacer(modifier = modifier.height(12.dp))
        HorizontalDivider(color = MaterialTheme.colorScheme.outline)
    }
}

@Preview
@Composable
private fun QuizResultListHeaderViewPreview() {
    MemorizingVocaTheme {
        QuizResultListHeaderView(
            incorrectCount = 0,
            blindMode = false,
            onBlindModeChange = {},
            onAllBookmarkClick = {},
        )
    }
}
