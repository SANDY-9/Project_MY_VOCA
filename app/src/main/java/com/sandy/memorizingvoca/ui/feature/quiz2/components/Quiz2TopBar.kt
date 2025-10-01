package com.sandy.memorizingvoca.ui.feature.quiz2.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sandy.memorizingvoca.ui.common.MyNavigationButton
import com.sandy.memorizingvoca.ui.theme.Pink100

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
        MyNavigationButton(
            onNavigateBack = onNavigateBack,
        )
        Text(
            modifier = modifier.weight(1f),
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
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
        modifier = modifier.padding(end = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "정답 개수",
            fontSize = 14.sp,
            color = Color.DarkGray,
        )
        Spacer(modifier = modifier.width(8.dp))
        Text(
            text = "$correctCount",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = Pink100,
        )
        Text(
            text = " / ",
            fontSize = 14.sp,
            color = Color.Gray,
        )
        Text(
            text = "$total",
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
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