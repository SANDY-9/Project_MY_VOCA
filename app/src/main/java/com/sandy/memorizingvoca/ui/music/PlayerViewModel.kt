package com.sandy.memorizingvoca.ui.music

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.session.MediaController
import com.google.common.util.concurrent.ListenableFuture
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
class PlayerViewModel @Inject constructor(
    private val mediaControllerFuture: ListenableFuture<MediaController>,
    @ApplicationContext context: Context,
): ViewModel() {

    private val _playerState = MutableStateFlow(PlayerState())
    val playerState = _playerState.asStateFlow()

    private val playerListener = object : Player.Listener {
        override fun onIsPlayingChanged(isPlaying: Boolean) {
            _playerState.value = playerState.value.copy(isPlaying = isPlaying)
        }

        override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
            super.onMediaItemTransition(mediaItem, reason)
            _playerState.value = playerState.value.copy(
                currentMediaItem = mediaItem,
                currentMediaItemIndex = playerState.value.mediaItems.indexOfFirst { it == mediaItem },
                totalDuration = mediaController?.duration?.coerceAtLeast(0) ?: 0
            )
        }
    }

    private var mediaController: MediaController? = null
    init {
        mediaControllerFuture.addListener({
            mediaController = mediaControllerFuture.get().apply {
                addListener(playerListener)
                setMediaItems(playerState.value.mediaItems)
                prepare()
            }
        }, ContextCompat.getMainExecutor(context))
    }

    // 재생/일시정지 토글
    fun playPause() {
        if (mediaController?.isPlaying == true) {
            mediaController?.pause()
        } else {
            mediaController?.play()
        }
    }

    private var positionUpdateJob: Job? = null
    private fun startPositionUpdates() {
        positionUpdateJob?.cancel() // 기존 작업이 있다면 취소
        positionUpdateJob = viewModelScope.launch {
            while (isActive) {
                _playerState.value = _playerState.value.copy(
                    currentPosition = mediaController?.currentPosition?.coerceAtLeast(0) ?: 0
                )
                delay(1000L) // 1초마다 업데이트
            }
        }
    }
    private fun stopPositionUpdates() {
        positionUpdateJob?.cancel()
    }

    fun seekTo(position: Long) {
        mediaController?.seekTo(position)
    }

    fun skipToNext() {
        mediaController?.seekToNextMediaItem()
    }

    fun skipToPrevious() {
        mediaController?.seekToPreviousMediaItem()
    }

    // 반복 상태 토글
    fun setRepeatMode() {
        // REPEAT_MODE_OFF, REPEAT_MODE_ONE, REPEAT_MODE_ALL
        val nextMode = (mediaController?.repeatMode?.plus(1))?.rem(3) ?: Player.REPEAT_MODE_OFF
        mediaController?.repeatMode = nextMode
    }

    override fun onCleared() {
        super.onCleared()
        stopPositionUpdates()
        mediaController?.removeListener(playerListener)
        mediaController?.release()
    }
}