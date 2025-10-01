package com.sandy.memorizingvoca.ui.extensions

import android.view.SoundEffectConstants
import androidx.compose.runtime.Composable
import androidx.compose.ui.hapticfeedback.HapticFeedbackType

import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.LocalView

@Composable
fun clickEffect(
    onClick: () -> Unit,
): () -> Unit {
    val haptic = LocalHapticFeedback.current
    val view = LocalView.current
    return {
        view.playSoundEffect(SoundEffectConstants.CLICK)
        haptic.performHapticFeedback(HapticFeedbackType.ContextClick)
        onClick()
    }
}
