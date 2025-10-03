package com.sandy.memorizingvoca.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sandy.memorizingvoca.ui.extensions.FolderShape
import com.sandy.memorizingvoca.ui.extensions.folderShape
import com.sandy.memorizingvoca.ui.extensions.singleClick
import com.sandy.memorizingvoca.ui.theme.Gray30
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme
import com.sandy.memorizingvoca.ui.theme.Pink80

@Composable
fun DayFolderCard(
    day: Int,
    count: Int,
    exist: Boolean,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(
                    top = 1.dp,
                    start = 8.dp,
                    end = 8.dp,
                    bottom = 6.dp,
                )
                .folderShape(
                    backgroundColor = if(exist) {
                        MaterialTheme.colorScheme.secondary
                    }
                    else {
                        MaterialTheme.colorScheme.surfaceVariant
                    },
                    folderColor = if(exist) {
                        MaterialTheme.colorScheme.primaryContainer
                    }
                    else {
                        MaterialTheme.colorScheme.surfaceContainer
                    },
                    strokeColor = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                .clip(FolderShape())
                .singleClick (onClick = onItemClick),
        ) {
            if(exist) {
                Text(
                    modifier = Modifier.padding(start = 5.dp, top = 2.dp),
                    text = "$count",
                    fontSize = 10.sp,
                    color = Color.LightGray,
                )
            }
        }
        Text(
            text = "DAY " + String.format("%02d", day),
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.onSecondary,
        )
    }

}

@Composable
@Preview
private fun Preview() {
    MemorizingVocaTheme {
        Row(
            modifier = Modifier.height(120.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            DayFolderCard(
                modifier = Modifier.weight(1f).height(100.dp),
                day = 6,
                count = 10,
                exist = true,
                onItemClick = {},
            )
            DayFolderCard(
                modifier = Modifier.weight(1f),
                day = 6,
                count = 0,
                exist = false,
                onItemClick = {},
            )
            DayFolderCard(
                modifier = Modifier.weight(1f),
                day = 6,
                count = 1,
                exist = true,
                onItemClick = {},
            )
        }
    }
}