package com.sandy.memorizingvoca.utils

import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.annotation.RawRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.sandy.memorizingvoca.R

class AnswerEffectManager(
    context: Context,
    @RawRes soundResId: Int,
) {
    @Suppress("DEPRECATION")
    private val vibrator = if (Build.VERSION.SDK_INT >= 31) {
        val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        vibratorManager.defaultVibrator
    } else {
        context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    private val mediaPlayer = MediaPlayer.create(context, soundResId)

    fun vibrate(duration: Long = 500L) {
        if(vibrator.hasVibrator()) {
            val effect = VibrationEffect.createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE)
            vibrator.vibrate(effect)
        }
    }

    fun playSound() {
        mediaPlayer.start()
    }

    fun release() {
        vibrator.cancel()
        mediaPlayer.release()
    }
}

@Composable
fun rememberAnswerEffectManager(
    context: Context = LocalContext.current,
    @RawRes soundResId: Int = R.raw.correct,
): AnswerEffectManager {
    val answerEffectManager = remember {
        AnswerEffectManager(
            context = context,
            soundResId = soundResId,
        )
    }
    DisposableEffect(Unit) {
        onDispose {
            answerEffectManager.release()
        }
    }
    return answerEffectManager
}