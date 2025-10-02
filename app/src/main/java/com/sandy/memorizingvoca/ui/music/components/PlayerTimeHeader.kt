package com.sandy.memorizingvoca.ui.music.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun PlayerTimeHeader(
    currentTime: String,
    totalTime: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp)
    ) {
        Text(
            text = currentTime,
            fontSize = 10.sp,
            lineHeight = 10.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Gray
        )
        Spacer(modifier = modifier.weight(1f))
        Text(
            text = totalTime,
            fontSize = 10.sp,
            lineHeight = 10.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Gray
        )
    }
}