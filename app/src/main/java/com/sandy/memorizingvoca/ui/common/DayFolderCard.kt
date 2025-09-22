package com.sandy.memorizingvoca.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sandy.memorizingvoca.ui.theme.Gray10
import com.sandy.memorizingvoca.ui.theme.Gray30
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme
import com.sandy.memorizingvoca.ui.theme.Pink80
import com.sandy.memorizingvoca.ui.theme.PyeoginGothic
import com.sandy.memorizingvoca.ui.theme.roundedCornerShape10
import com.sandy.memorizingvoca.ui.theme.roundedCornerShape12

@Composable
fun DayFolderCard(
    day: Int,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.height(80.dp),
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .fillMaxHeight(0.95f)
                .background(
                    color = Pink80,
                    shape = roundedCornerShape12
                ),
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .fillMaxHeight(0.5f)
                .background(
                    color = Gray10,
                    shape = roundedCornerShape10
                )
                .border(
                    width = 1.dp,
                    color = Gray30,
                    shape = roundedCornerShape10,
                ),
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .fillMaxWidth()
                .fillMaxHeight(0.80f)
                .background(
                    color = Color.White,
                    shape = roundedCornerShape12
                )
                .border(
                    width = 1.dp,
                    color = Gray30,
                    shape = roundedCornerShape12,
                )
                .clip(roundedCornerShape12)
                .clickable(onClick = onItemClick),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Day " + String.format("%02d", day),
                fontFamily = PyeoginGothic,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                color = Color.DarkGray,
            )
        }
    }

}

@Composable
@Preview
private fun Preview() {
    MemorizingVocaTheme {
        Row(
            modifier = Modifier.height(100.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            DayFolderCard(
                modifier = Modifier.weight(1f),
                day = 6,
                onItemClick = {},
            )
            DayFolderCard(
                modifier = Modifier.weight(1f),
                day = 6,
                onItemClick = {},
            )
            DayFolderCard(
                modifier = Modifier.weight(1f),
                day = 6,
                onItemClick = {},
            )
        }
    }
}