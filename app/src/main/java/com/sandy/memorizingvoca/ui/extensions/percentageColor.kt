package com.sandy.memorizingvoca.ui.extensions

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
internal fun Int.percentageColor(): Color = when (this) {
    100 -> MaterialTheme.colorScheme.surfaceDim // 100일 때
    in 80..< 100 -> MaterialTheme.colorScheme.secondary // 80부터 99까지
    in 50..< 80 -> MaterialTheme.colorScheme.tertiary // 50부터 79까지
    else -> MaterialTheme.colorScheme.surfaceVariant // 50 미만
}