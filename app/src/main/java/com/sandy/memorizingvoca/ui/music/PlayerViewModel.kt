package com.sandy.memorizingvoca.ui.music

import android.content.Context
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.session.MediaController
import androidx.media3.session.SessionCommand
import com.google.common.util.concurrent.ListenableFuture
import com.sandy.memorizingvoca.service.MediaPlaybackService
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class PlayerViewModel @Inject constructor(
    private val mediaControllerFuture: ListenableFuture<MediaController>,
    @ApplicationContext private val context: Context,
): ViewModel() {

    private val _playerState = MutableStateFlow(PlayerState())
    val playerState = _playerState.asStateFlow()

    private var mediaController: MediaController? = null
    init {
        mediaControllerFuture.addListener({
            mediaController = mediaControllerFuture.get().apply {
                if(mediaItemCount == 0) {
                    setMediaItems(playerState.value.mediaItems)
                    repeatMode = Player.REPEAT_MODE_OFF
                    prepare()
                }
                else {
                    _playerState.value = PlayerState(
                        isPlaying = isPlaying,
                        repeatMode = repeatMode.state(),
                        currentPosition = currentPosition,
                        totalDuration = duration,
                        currentMediaItemIndex = currentMediaItemIndex,
                        currentMediaItem = currentMediaItem,
                    )
                }
                addListener(playerListener)
            }
        }, ContextCompat.getMainExecutor(context))
    }

    private val playerListener = object : Player.Listener {
        override fun onIsPlayingChanged(isPlaying: Boolean) {
            _playerState.value = playerState.value.copy(isPlaying = isPlaying)
            when {
                isPlaying -> startPositionUpdates()
                else -> stopPositionUpdates()
            }
        }

        override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
            super.onMediaItemTransition(mediaItem, reason)
            val isRepeatModeOff = playerState.value.repeatMode.num == Player.REPEAT_MODE_OFF &&
                    reason == Player.MEDIA_ITEM_TRANSITION_REASON_AUTO
            if(isRepeatModeOff) {
                seekTo(0f)
                mediaController?.pause()
                return
            }
            _playerState.value = playerState.value.copy(
                currentMediaItem = mediaItem,
                currentMediaItemIndex = playerState.value.mediaItems.indexOfFirst { it == mediaItem },
                totalDuration = mediaController?.duration?.coerceAtLeast(0) ?: 0
            )
        }

        override fun onPlaybackStateChanged(playbackState: Int) {
            if (playbackState == Player.STATE_READY) {
                _playerState.value = playerState.value.copy(
                    totalDuration = mediaController?.duration?.coerceAtLeast(0) ?: 0,
                )
            }
        }

        override fun onRepeatModeChanged(repeatMode: Int) {
            super.onRepeatModeChanged(repeatMode)
            _playerState.value = playerState.value.copy(
                repeatMode = repeatMode.state()
            )
        }
    }

    private fun Int.state(): PlayerState.RepeatMode {
        return when(this) {
            Player.REPEAT_MODE_OFF -> PlayerState.RepeatMode.REPEAT_MODE_OFF
            Player.REPEAT_MODE_ONE -> PlayerState.RepeatMode.REPEAT_MODE_ONE
            else -> PlayerState.RepeatMode.REPEAT_MODE_ALL
        }
    }

    private var positionUpdateJob: Job? = null
    private fun startPositionUpdates() {
        positionUpdateJob?.cancel() // 기존 작업이 있다면 취소
        positionUpdateJob = viewModelScope.launch {
            while (isActive) {
                _playerState.value = playerState.value.copy(
                    currentPosition = mediaController?.currentPosition?.coerceAtLeast(0) ?: 0
                )
                delay(1000L) // 1초마다 업데이트
            }
        }
    }

    private fun stopPositionUpdates() {
        positionUpdateJob?.cancel()
    }

    suspend fun initPlayer() {
        if(mediaController?.isPlaying == true) return
        mediaController?.run {
            prepare()
            seekTo(0, 0)
            repeatMode = Player.REPEAT_MODE_OFF
            delay(200L)
            play()
        }
    }

    fun closePlayer() {
        stopPositionUpdates()
        val sessionCommand = SessionCommand(
            MediaPlaybackService.ACTION_CLOSE_PLAYER_AND_NOTIFICATION,
            Bundle.EMPTY,
        )
        mediaController?.sendCustomCommand(sessionCommand, Bundle.EMPTY)
    }

    fun playPause() {
        mediaController?.run {
            if(isPlaying) {
                pause()
            }
            else {
                prepare()
                play()
            }
        }
    }

    fun seekTo(value: Float) {
        val position = (value * playerState.value.totalDuration).toLong()
        _playerState.value = playerState.value.copy(
            currentPosition = position
        )
        mediaController?.seekTo(position)
    }

    fun skipToNext() {
        if(playerState.value.currentMediaItemIndex == playerState.value.mediaItems.lastIndex) {
            mediaController?.seekTo(0, 0)
            return
        }
        mediaController?.seekToNextMediaItem()
    }

    fun skipToPrevious() {
        if(playerState.value.currentMediaItemIndex == 0) {
            mediaController?.seekTo(playerState.value.mediaItems.lastIndex, 0)
            return
        }
        mediaController?.seekToPreviousMediaItem()
    }

    fun setRepeatMode() {
        val nextMode = (mediaController?.repeatMode?.plus(1))?.rem(3) ?: Player.REPEAT_MODE_OFF
        mediaController?.repeatMode = nextMode
    }

    override fun onCleared() {
        super.onCleared()
        stopPositionUpdates()
        mediaController?.removeListener(playerListener)
        mediaController?.release()
        MediaController.releaseFuture(mediaControllerFuture)
        MediaPlaybackService.destroyService(context = context)
    }
}