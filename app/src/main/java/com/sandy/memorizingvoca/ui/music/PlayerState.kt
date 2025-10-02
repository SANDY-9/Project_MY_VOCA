package com.sandy.memorizingvoca.ui.music

import androidx.media3.common.MediaItem
import com.sandy.memorizingvoca.R

data class PlayerState(
    val isPlaying: Boolean = false,
    val isLooping: Boolean = false,
    val currentPosition: Long = 0,
    val totalDuration: Long = 0,
    val mediaItems: List<MediaItem> = listOf(
        MediaItem.fromUri("android.resource://com.sandy.memorizingvoca/${R.raw.day_01}"),
        MediaItem.fromUri("android.resource://com.sandy.memorizingvoca/${R.raw.day_01}"),
    ),
    val currentMediaItemIndex: Int = 0,
    val currentMediaItem: MediaItem? = mediaItems.getOrNull(currentMediaItemIndex),
)
