package com.sandy.memorizingvoca.ui.extensions

import android.view.SoundEffectConstants
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ripple
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.LocalView
import kotlinx.coroutines.delay

fun Modifier.longPressClickable(
    intervalMillis: Long = 100L,
    onLongPress: () -> Unit,
    onClick: () -> Unit,
): Modifier = composed {
    val view = LocalView.current
    val haptic = LocalHapticFeedback.current
    val interactionSource = remember { MutableInteractionSource() }
    var isLongPressed by remember { mutableStateOf(false) }
    LaunchedEffect(isLongPressed) {
        if (isLongPressed) {
            delay(intervalMillis)
            while (isLongPressed) {
                onLongPress()
                delay(intervalMillis)
            }
        }
    }
    this
        .clip(CircleShape)
        .pointerInput(Unit) {
            detectTapGestures(
                onPress = {
                    view.playSoundEffect(SoundEffectConstants.CLICK)
                    haptic.performHapticFeedback(HapticFeedbackType.ContextClick)
                    val press = PressInteraction.Press(it)
                    interactionSource.emit(press)
                    isLongPressed = true
                    try {
                        awaitRelease()
                    } finally {
                        isLongPressed = false
                        interactionSource.emit(PressInteraction.Release(press))
                    }
                },
                onTap = {
                    onClick()
                }
            )
        }
        .indication(
            interactionSource = interactionSource,
            indication = ripple()
        )
}