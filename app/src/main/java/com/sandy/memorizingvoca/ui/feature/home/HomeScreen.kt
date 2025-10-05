package com.sandy.memorizingvoca.ui.feature.home

import android.content.Context
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sandy.memorizingvoca.ui.common.DayFolderCard
import com.sandy.memorizingvoca.ui.common.MyTextButton
import com.sandy.memorizingvoca.ui.music.MyMiniMusicPlayer
import com.sandy.memorizingvoca.ui.music.PlayerState
import com.sandy.memorizingvoca.ui.music.PlayerViewModel
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme

@Composable
internal fun HomeRoute(
    onAppFinish: () -> Unit,
    onNavigateList: (Int) -> Unit,
    playerViewModel: PlayerViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    BackPressTwiceToExit(
        context = context,
        onFinish = onAppFinish,
    )

    val days by homeViewModel.days.collectAsStateWithLifecycle()
    val musicPlayerOn by homeViewModel.musicPlayer.collectAsStateWithLifecycle()

    val playerState by playerViewModel.playerState.collectAsStateWithLifecycle()

    LaunchedEffect(playerState.isPlaying) {
        if(playerState.isPlaying) {
            homeViewModel.onPlayerOnAndOffChange(true)
        }
    }

    LaunchedEffect(musicPlayerOn) {
        when {
            musicPlayerOn -> playerViewModel.openPlayer()
            else -> playerViewModel.closePlayer()
        }
    }
    HomeScreen(
        days = days,
        playerState = playerState,
        musicPlayerOn = musicPlayerOn,
        onMusicPlayerOnChange = homeViewModel::onPlayerOnAndOffChange,
        onPlayingChange = playerViewModel::playPause,
        onDurationChange = playerViewModel::seekTo,
        onNextButtonClick = playerViewModel::skipToNext,
        onPrevButtonClick = playerViewModel::skipToPrevious,
        onRepeatModeChange = playerViewModel::setRepeatMode,
        onFolderItemClick = onNavigateList,
    )
}

@Composable
private fun HomeScreen(
    days: Map<Int, Int>,
    playerState: PlayerState,
    musicPlayerOn: Boolean,
    onMusicPlayerOnChange: (Boolean) -> Unit,
    onFolderItemClick: (Int) -> Unit,
    onPlayingChange: () -> Unit,
    onDurationChange: (Float) -> Unit,
    onNextButtonClick: () -> Unit,
    onPrevButtonClick: () -> Unit,
    onRepeatModeChange: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 8.dp)
                .height(56.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "심슨 VOCA 2026 단어장",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            )
            Spacer(modifier = modifier.weight(1f))
            MyTextButton(
                title = "♪ MP3",
                onClick = {
                    onMusicPlayerOnChange(!musicPlayerOn)
                },
            )
        }
        LazyVerticalGrid(
            modifier = modifier
                .padding(horizontal = 16.dp)
                .weight(1f),
            contentPadding = PaddingValues(bottom = 16.dp),
            columns = GridCells.Adaptive(70.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            days.forEach { (day, count) ->
                item(day) {
                    DayFolderCard(
                        modifier = modifier.height(75.dp),
                        day = day,
                        count = count,
                        exist = count != 0,
                        onItemClick = {
                            onFolderItemClick(day)
                        },
                    )
                }
            }
        }
        AnimatedVisibility(
            visible = musicPlayerOn ?: false,
            enter = slideInHorizontally(
                initialOffsetX = { it },
            ) + fadeIn(
                initialAlpha = 0.3f
            ),
            exit = fadeOut() + slideOutHorizontally(
                targetOffsetX = { it },
            ),
        ) {
            MyMiniMusicPlayer(
                playerState = playerState,
                onPlayingChange = onPlayingChange,
                onDurationChange = onDurationChange,
                onNextButtonClick = onNextButtonClick,
                onPrevButtonClick = onPrevButtonClick,
                onRepeatModeChange = onRepeatModeChange,
                onDismiss = { onMusicPlayerOnChange(false) },
            )
        }
    }
}

@Composable
private fun BackPressTwiceToExit(
    context: Context,
    onFinish: () -> Unit,
) {
    var backPressedState by remember { mutableStateOf(true) }
    var backPressedTime = 0L

    BackHandler(enabled = backPressedState) {
        if(System.currentTimeMillis() - backPressedTime <= 2000L) {
            // 앱 종료
            onFinish()
        } else {
            backPressedState = true
            Toast.makeText(context, "한 번 더 누르시면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show()
        }
        backPressedTime = System.currentTimeMillis()
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    MemorizingVocaTheme {
        HomeScreen(
            days = (1..10).associateWith { it % 2 },
            musicPlayerOn = true,
            playerState = PlayerState(),
            onMusicPlayerOnChange = {},
            onFolderItemClick = {},
            onPlayingChange = {},
            onDurationChange = {},
            onNextButtonClick = {},
            onPrevButtonClick = {},
            onRepeatModeChange = {},
        )
    }
}