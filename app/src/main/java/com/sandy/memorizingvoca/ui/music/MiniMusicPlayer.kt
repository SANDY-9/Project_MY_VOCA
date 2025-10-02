package com.sandy.memorizingvoca.ui.music

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sandy.memorizingvoca.R
import com.sandy.memorizingvoca.ui.extensions.clickEffect
import com.sandy.memorizingvoca.ui.extensions.noRippleClickable
import com.sandy.memorizingvoca.ui.music.components.EqualizerBackground
import com.sandy.memorizingvoca.ui.music.components.HorizontalMusicBar
import com.sandy.memorizingvoca.ui.music.components.PlayerContentView
import com.sandy.memorizingvoca.ui.music.components.PlayerTimeHeader
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme
import com.sandy.memorizingvoca.ui.theme.Pink100

@Composable
fun MiniMusicPlayer(
    modifier: Modifier = Modifier,
) {
    MiniMusicPlayerRoute(
        modifier = modifier,
    )
}

@Composable
internal fun MiniMusicPlayerRoute(
    viewModel: PlayerViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    val playerState by viewModel.playerState.collectAsStateWithLifecycle()
    MusicPlayerUI(
        isPlaying = playerState.isPlaying,
        repeatMode = playerState.repeatMode,
        dayTitle = playerState.dayTitle,
        currentTime = playerState.currentTime,
        totalTime = playerState.totalTime,
        currentDuration = playerState.currentDuration,
        onPlayingChange = viewModel::playPause,
        onValueChange = viewModel::seekTo,
        onNextButtonClick = viewModel::skipToNext,
        onPrevButtonClick = viewModel::skipToPrevious,
        onRepeatModeChange = viewModel::setRepeatMode,
        modifier = modifier,
    )
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
    onValueChange: (Float) -> Unit,
    onNextButtonClick: () -> Unit,
    onPrevButtonClick: () -> Unit,
    onRepeatModeChange: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxWidth()
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
                onValueChange = onValueChange,
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
            onValueChange = {},
            onNextButtonClick = {},
            onPrevButtonClick = {},
            onRepeatModeChange = {},
        )
    }
}
