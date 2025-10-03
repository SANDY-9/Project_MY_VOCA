package com.sandy.memorizingvoca.ui.extensions

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
internal fun Int.percentageColor(): Color = when (this) {
    100 -> MaterialTheme.colorScheme.scrim // 100일 때
    in 90..< 100 -> MaterialTheme.colorScheme.secondary // 90부터 99까지
    in 50..< 90 -> MaterialTheme.colorScheme.tertiary // 50부터 89까지
    else -> MaterialTheme.colorScheme.surfaceVariant // 50 미만
}