package com.sandy.memorizingvoca.utils

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.Locale

class TTSManager(
    context: Context,
    private val scope: CoroutineScope?,
) {
    private var tts: TextToSpeech? = null
    private var job: Job? = null

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
        stop()
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, text)
    }

    fun onDone(
        onDone: suspend (String?) -> Unit,
    ) {
        tts?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
            override fun onDone(p0: String?) {
                job = scope?.launch {
                    onDone(p0)
                }
            }
            override fun onError(p0: String?) { /* NO-OP */ }
            override fun onStart(p0: String?) { /* NO-OP */ }
        })
    }

    fun stop() {
        tts?.stop()
        job?.cancel()
    }

    fun release() {
        stop()
        tts?.shutdown()
    }
}

@Composable
fun rememberTTSManager(
    context: Context = LocalContext.current,
    scope: CoroutineScope = rememberCoroutineScope(),
): TTSManager {
    val ttsManager = remember { TTSManager(context, scope) }
    DisposableEffect(Unit) {
        onDispose {
            ttsManager.release()
        }
    }
    return ttsManager
}