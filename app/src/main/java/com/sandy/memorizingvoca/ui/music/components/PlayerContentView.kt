package com.sandy.memorizingvoca.ui.music.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sandy.memorizingvoca.R
import com.sandy.memorizingvoca.ui.extensions.clickEffect
import com.sandy.memorizingvoca.ui.extensions.noRippleClickable
import com.sandy.memorizingvoca.ui.music.PlayerState
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme
import com.sandy.memorizingvoca.ui.theme.Pink100

@Composable
internal fun PlayerContentView(
    dayTitle: String,
    isPlaying: Boolean,
    repeatMode: PlayerState.RepeatMode,
    onPlayingChange: () -> Unit,
    onNextButtonClick: () -> Unit,
    onPrevButtonClick: () -> Unit,
    onRepeatModeChange: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 12.dp,
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = dayTitle,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.DarkGray,
        )
        Spacer(modifier = modifier.width(4.dp))
        IconButton(
            modifier = modifier.size(30.dp),
            onClick = onRepeatModeChange,
        ) {
            Icon(
                modifier = modifier.size(21.dp),
                painter = painterResource(repeatMode.res),
                contentDescription = null,
                tint = repeatMode.color,
            )
        }
        Spacer(modifier = modifier.weight(1f))
        IconButton(
            modifier = modifier.size(30.dp),
            onClick = onPrevButtonClick,
        ) {
            Icon(
                painter = painterResource(R.drawable.skip_previous_24px),
                contentDescription = null,
                tint = Pink100,
            )
        }
        IconButton(
            modifier = modifier.size(30.dp),
            onClick = onPlayingChange,
        ) {
            Icon(
                painter = painterResource(
                    if(isPlaying) R.drawable.pause_24px
                    else R.drawable.play_arrow_24px
                ),
                contentDescription = null,
                tint = Pink100,
            )
        }
        IconButton(
            modifier = modifier.size(30.dp),
            onClick = onNextButtonClick,
        ) {
            Icon(
                painter = painterResource(R.drawable.skip_next_24px),
                contentDescription = null,
                tint = Pink100,
            )
        }
    }
}

@Preview
@Composable
private fun PlayerContentViewPreview() {
    MemorizingVocaTheme {
        PlayerContentView(
            dayTitle = "DAY 01",
            isPlaying = true,
            repeatMode = PlayerState.RepeatMode.REPEAT_MODE_OFF,
            onPlayingChange = {},
            onNextButtonClick = {},
            onPrevButtonClick = {},
            onRepeatModeChange = {},
        )
    }
}
