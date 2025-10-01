package com.sandy.memorizingvoca.ui.extensions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback

fun Modifier.noRippleClickable(
    enabled: Boolean = true,
    onClick: () -> Unit,
) = composed {
    this.clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null,
        enabled = enabled,
        onClick = clickEffect { onClick() },
    )
}

fun Modifier.noRippleClickableNotSound(
    enabled: Boolean = true,
    onClick: () -> Unit,
) = composed {
    val haptic = LocalHapticFeedback.current
    this.clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null,
        enabled = enabled,
        onClick = {
            haptic.performHapticFeedback(HapticFeedbackType.ContextClick)
            onClick()
        },
    )
}