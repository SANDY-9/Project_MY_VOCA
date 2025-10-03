package com.sandy.memorizingvoca.ui.music

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sandy.memorizingvoca.ui.extensions.clickEffect
import com.sandy.memorizingvoca.ui.music.components.EqualizerBackground
import com.sandy.memorizingvoca.ui.music.components.HorizontalMusicBar
import com.sandy.memorizingvoca.ui.music.components.PlayerContentView
import com.sandy.memorizingvoca.ui.music.components.PlayerTimeHeader
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme

@Composable
fun MyMiniMusicPlayer(
    playerState: PlayerState,
    onPlayingChange: () -> Unit,
    onDurationChange: (Float) -> Unit,
    onNextButtonClick: () -> Unit,
    onPrevButtonClick: () -> Unit,
    onRepeatModeChange: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var swipeState by remember { mutableStateOf(SwipeToDismissBoxValue.Settled) }
    LaunchedEffect(swipeState) {
        if(swipeState == SwipeToDismissBoxValue.StartToEnd) {
            onDismiss()
        }
    }
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            swipeState = it
            it == SwipeToDismissBoxValue.StartToEnd
        }
    )
    SwipeToDismissBox(
        state = dismissState,
        enableDismissFromEndToStart = false,
        backgroundContent = {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(Color.Transparent),
            )
        }
    ) {
        MusicPlayerUI(
            isPlaying = playerState.isPlaying,
            repeatMode = playerState.repeatMode,
            dayTitle = playerState.dayTitle,
            currentDuration = playerState.currentDuration,
            currentTime = playerState.currentTime,
            totalTime = playerState.totalTime,
            onPlayingChange = onPlayingChange,
            onDurationChange = onDurationChange,
            onNextButtonClick = onNextButtonClick,
            onPrevButtonClick = onPrevButtonClick,
            onRepeatModeChange = onRepeatModeChange,
            modifier = modifier,
        )
    }
}

@Composable
private fun MusicPlayerUI(
    isPlaying: Boolean,
    repeatMode: PlayerState.RepeatMode,
    dayTitle: String,
    currentDuration: Float,
    currentTime: String,
    totalTime: String,
    onPlayingChange: () -> Unit,
    onDurationChange: (Float) -> Unit,
    onNextButtonClick: () -> Unit,
    onPrevButtonClick: () -> Unit,
    onRepeatModeChange: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp)
            .clipToBounds()
            .background(
                color = MaterialTheme.colorScheme.surface,
            )
    ) {
        if(isPlaying) {
            EqualizerBackground(
                modifier = Modifier
                    .fillMaxHeight(0.45f)
                    .align(Alignment.BottomStart),
            )
        }
        Column {
            HorizontalMusicBar(
                value = currentDuration,
                onValueChange = onDurationChange,
            )
            PlayerTimeHeader(
                currentTime = currentTime,
                totalTime = totalTime,
            )
            PlayerContentView(
                dayTitle = dayTitle,
                isPlaying = isPlaying,
                repeatMode = repeatMode,
                onPlayingChange = clickEffect(onPlayingChange),
                onNextButtonClick = clickEffect(onNextButtonClick),
                onPrevButtonClick = clickEffect(onPrevButtonClick),
                onRepeatModeChange = clickEffect(onRepeatModeChange),
            )
        }
    }
}



@Preview
@Composable
private fun MusicPlayerUIPreview() {
    MemorizingVocaTheme {
        MusicPlayerUI(
            isPlaying = true,
            repeatMode = PlayerState.RepeatMode.REPEAT_MODE_OFF,
            dayTitle = "DAY 01",
            currentTime = "00:00",
            totalTime = "05:04",
            currentDuration = 0.5f,
            onPlayingChange = {},
            onDurationChange = {},
            onNextButtonClick = {},
            onPrevButtonClick = {},
            onRepeatModeChange = {},
        )
    }
}
