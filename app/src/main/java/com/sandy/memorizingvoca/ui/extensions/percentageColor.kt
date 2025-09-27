package com.sandy.memorizingvoca.ui.extensions

import androidx.compose.ui.graphics.Color
import com.sandy.memorizingvoca.ui.theme.Gray30
import com.sandy.memorizingvoca.ui.theme.Pink40
import com.sandy.memorizingvoca.ui.theme.Pink80
import com.sandy.memorizingvoca.ui.theme.Pink90

internal fun Int.percentageColor(): Color = when (this) {
    100 -> Pink90 // 100일 때
    in 90..< 100 -> Pink80 // 90부터 99까지
    in 50..< 90 -> Pink40 // 50부터 89까지
    else -> Gray30 // 50 미만
}