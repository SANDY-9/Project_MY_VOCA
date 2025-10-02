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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.sandy.memorizingvoca.R
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme
import com.sandy.memorizingvoca.ui.theme.Pink100


@Composable
fun MiniMusicPlayer(
    modifier: Modifier = Modifier,
    viewModel: PlayerViewModel = hiltViewModel(),
) {
    MusicPlayerUI(modifier = modifier)
}

@Composable
private fun MusicPlayerUI(
    modifier: Modifier = Modifier,
) {
    var value by remember { mutableStateOf(0.75f) }
    Box(
        modifier = modifier.fillMaxWidth()
            .height(50.dp)
            .clipToBounds()
            .background(
                color = MaterialTheme.colorScheme.surface
            )
    ) {
        EqualizerBackground(
            modifier = modifier
                .fillMaxHeight(0.45f)
                .align(Alignment.BottomStart),
        )
        Column(
            modifier = modifier
        ) {
            HorizontalMusicBar(
                value = value,
                onValueChange = { value = it },
            )
            Text(
                modifier = modifier
                    .align(Alignment.End)
                    .padding(end = 4.dp),
                text = "05:04",
                fontSize = 10.sp,
                lineHeight = 10.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray
            )
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = modifier.alignByBaseline(),
                    text = "DAY 01",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.DarkGray,
                )
                Spacer(modifier = modifier.width(4.dp))
                Icon(
                    modifier = modifier.size(21.dp),
                    painter = painterResource(R.drawable.repeat_one_24px),
                    contentDescription = null,
                    tint = Pink100,
                )
                Spacer(modifier = modifier.weight(1f))
                Icon(
                    painter = painterResource(R.drawable.skip_previous_24px),
                    contentDescription = null,
                    tint = Pink100,
                )
                Icon(
                    painter = painterResource(R.drawable.pause_24px),
                    contentDescription = null,
                    tint = Pink100,
                )
                Icon(
                    painter = painterResource(R.drawable.skip_next_24px),
                    contentDescription = null,
                    tint = Pink100,
                )
            }
        }
    }
}



@Preview
@Composable
private fun MusicPlayerUIPreview() {
    MemorizingVocaTheme {
        MusicPlayerUI()
    }
}
