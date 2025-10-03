package com.sandy.memorizingvoca.ui.music

import androidx.annotation.DrawableRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.media3.common.MediaItem
import com.sandy.memorizingvoca.R
import java.util.Locale
import java.util.concurrent.TimeUnit

data class PlayerState(
    val isPlaying: Boolean = false,
    val repeatMode: RepeatMode = RepeatMode.REPEAT_MODE_OFF,
    val currentPosition: Long = 0,
    val totalDuration: Long = 0,
    val mediaItems: List<MediaItem> = List(50) { num ->
        MediaItem.fromUri("asset:///day_${String.format(Locale.ENGLISH, "%02d", num+1)}.mp3")
    },
    val currentMediaItemIndex: Int = 0,
    val currentMediaItem: MediaItem? = mediaItems.getOrNull(currentMediaItemIndex),
) {
    val currentTime: String = currentPosition.formatDuration()
    val currentDuration: Float = if(totalDuration == 0L) 0f else ( currentPosition.toFloat() / totalDuration.toFloat() )
    val totalTime: String = totalDuration.formatDuration()
    val dayTitle: String = "DAY " + String.format(Locale.ENGLISH, "%02d", currentMediaItemIndex + 1)
    enum class RepeatMode(
        val num: Int = -1,
        @DrawableRes val res: Int = -1,
    ) {
        REPEAT_MODE_OFF(
            num = 0,
            res = R.drawable.repeat_24px,
        ),
        REPEAT_MODE_ONE(
            num = 1,
            res = R.drawable.repeat_one_24px,
        ),
        REPEAT_MODE_ALL(
            num = 2,
            res = R.drawable.repeat_24px,
        ),
    }
}

@Composable
internal fun PlayerState.RepeatMode.color(): Color = when(this) {
    PlayerState.RepeatMode.REPEAT_MODE_OFF -> Color.Gray
    else -> MaterialTheme.colorScheme.primary
}

private fun Long.formatDuration(): String {
    val minutes = TimeUnit.MILLISECONDS.toMinutes(this)
    val seconds = TimeUnit.MILLISECONDS.toSeconds(this) % 60
    return String.format(Locale.ENGLISH, "%02d:%02d", minutes, seconds)
}
