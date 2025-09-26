package com.sandy.memorizingvoca.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.sandy.memorizingvoca.ui.extensions.FolderShape
import com.sandy.memorizingvoca.ui.extensions.folderShape
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme

@Composable
fun DayFolderCard(
    day: Int,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .folderShape()
            .clip(FolderShape())
            .clickable(onClick = onItemClick)
            .padding(16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Day " + String.format("%02d", day),
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            color = Color.DarkGray,
        )
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
                modifier = Modifier.weight(1f).height(100.dp),
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