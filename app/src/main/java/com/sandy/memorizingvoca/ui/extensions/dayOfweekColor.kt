package com.sandy.memorizingvoca.ui.extensions

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.sandy.memorizingvoca.data.model.DayOfWeek

@Composable
internal fun DayOfWeek.color(): Color {
    return when(this) {
        DayOfWeek.SUNDAY -> MaterialTheme.colorScheme.error
        DayOfWeek.SATURDAY -> MaterialTheme.colorScheme.errorContainer
        else -> MaterialTheme.colorScheme.onSecondary
    }
}

@Composable
internal fun DayOfWeek.onColor(): Color {
    return when(this) {
        DayOfWeek.SUNDAY -> MaterialTheme.colorScheme.onError
        DayOfWeek.SATURDAY -> MaterialTheme.colorScheme.onErrorContainer
        else -> Color.DarkGray
    }
}