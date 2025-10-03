package com.sandy.memorizingvoca.ui.extensions

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.sandy.memorizingvoca.data.model.DayOfWeek
import com.sandy.memorizingvoca.ui.theme.DarkBlue
import com.sandy.memorizingvoca.ui.theme.DarkRed

@Composable
internal fun DayOfWeek.color(): Color {
    return when(this) {
        DayOfWeek.SUNDAY -> DarkRed
        DayOfWeek.SATURDAY -> DarkBlue
        else -> MaterialTheme.colorScheme.onSecondary
    }
}