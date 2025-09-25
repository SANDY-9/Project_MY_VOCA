package com.sandy.memorizingvoca.ui.feature.quiz2.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sandy.memorizingvoca.ui.theme.Pink100
import com.sandy.memorizingvoca.ui.theme.PyeoginGothic

@Composable
internal fun Quiz2TopBar(
    title: String,
    correctCount: Int,
    totalCount: Int,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = onNavigateBack,
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                contentDescription = null,
                tint = Color.Black,
            )
        }
        Text(
            modifier = modifier.weight(1f),
            text = title,
            fontFamily = PyeoginGothic,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            letterSpacing = (-0.1).sp,
        )
        Spacer(modifier = modifier.width(8.dp))
        ScoreView(
            correctCount = correctCount,
            total = totalCount,
        )
    }
}

@Composable
private fun ScoreView(
    correctCount: Int,
    total: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "정답 개수",
            fontSize = 16.sp,
            color = Color.DarkGray,
        )
        Spacer(modifier = modifier.width(8.dp))
        Text(
            text = "$correctCount",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Pink100,
        )
        Text(
            text = " / ",
            fontSize = 16.sp,
            color = Color.Gray,
        )
        Text(
            text = "$total",
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
        )
    }
}

@Composable
@Preview
private fun Quiz2TopBarPreview() {
    Quiz2TopBar(
        title = "Day 01",
        totalCount = 50,
        correctCount = 14,
        onNavigateBack = {},
    )
}