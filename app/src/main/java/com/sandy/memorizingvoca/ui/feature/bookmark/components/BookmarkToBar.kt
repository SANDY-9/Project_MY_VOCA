package com.sandy.memorizingvoca.ui.feature.bookmark.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sandy.memorizingvoca.ui.common.MyTextButton
import com.sandy.memorizingvoca.ui.extensions.noRippleClickable
import com.sandy.memorizingvoca.ui.resources.Visibility
import com.sandy.memorizingvoca.ui.resources.VisibilityOff
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme

@Composable
internal fun BookmarkTopBar(
    blindMode: Boolean,
    bookmarkCount: Int,
    fullScreenEnabled: Boolean,
    quiz1Enabled: Boolean,
    quiz2Enabled: Boolean,
    onBlindModeChange: (Boolean) -> Unit,
    onNavigateFullScreen: () -> Unit,
    onNavigateQuiz1: () -> Unit,
    onNavigateQuiz2: () -> Unit,
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
            text = "북마크(",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
        )
        Text(
            text = "$bookmarkCount",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.primary,
        )
        Text(
            text = ")",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
        )
        if (fullScreenEnabled) {
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
            Spacer(
                modifier = modifier.weight(1f),
            )
            MyTextButton(
                title = "Full",
                onClick = onNavigateFullScreen,
            )
        }
        if(quiz1Enabled) {
            MyTextButton(
                title = "Quiz1",
                onClick = onNavigateQuiz1,
            )
            if(!quiz2Enabled) {
                Spacer(modifier = modifier.width(8.dp))
            }
        }
        if(quiz2Enabled) {
            MyTextButton(
                title = "Quiz2",
                onClick = onNavigateQuiz2,
            )
            Spacer(modifier = modifier.width(8.dp))
        }
    }
}

@Preview
@Composable
private fun BookmarkTopBarPreview() {
    MemorizingVocaTheme {
        BookmarkTopBar(
            blindMode = false,
            fullScreenEnabled = true,
            quiz1Enabled = true,
            quiz2Enabled = true,
            bookmarkCount = 3,
            onBlindModeChange = {},
            onNavigateFullScreen = {},
            onNavigateQuiz1 = {},
            onNavigateQuiz2 = {},
        )
    }
}
