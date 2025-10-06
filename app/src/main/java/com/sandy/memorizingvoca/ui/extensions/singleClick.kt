package com.sandy.memorizingvoca.ui.extensions

import android.os.SystemClock
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun singleClick(
    intervalMillis: Long = 700L,
    onClick: () -> Unit,
): () -> Unit {
    var lastClickTime by remember { mutableLongStateOf(0L) }
    return clickEffect {
        val currentTime = SystemClock.elapsedRealtime()
        if (currentTime - lastClickTime > intervalMillis) {
            lastClickTime = currentTime
            onClick()
        }
    }
}

@Composable
fun Modifier.singleClick(
    interval: Long = 700L,
    onClick: () -> Unit
): Modifier {
    var lastClickTime by remember { mutableLongStateOf(0L) }
    return this.clickable(
        onClick = clickEffect {
            val currentTime = SystemClock.elapsedRealtime()
            if (currentTime - lastClickTime > interval) {
                lastClickTime = currentTime
                onClick()
            }
        }
    )
}