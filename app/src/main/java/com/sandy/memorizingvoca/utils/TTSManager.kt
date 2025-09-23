package com.sandy.memorizingvoca.utils

import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import java.util.Locale

class TTSManager(
    context: Context
) {
    private var tts: TextToSpeech? = null

    init {
        tts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts?.run {
                    language = Locale.US
                    setPitch(0.95f)
                    setSpeechRate(0.7f)
                }

            }
        }
    }

    fun speak(text: String) {
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    fun release() {
        tts?.stop()
        tts?.shutdown()
    }

}

@Composable
fun rememberTTSManager(
    context: Context = LocalContext.current,
): TTSManager {
    val ttsManager = remember { TTSManager(context) }
    DisposableEffect(Unit) {
        onDispose {
            ttsManager.release()
        }
    }
    return ttsManager
}