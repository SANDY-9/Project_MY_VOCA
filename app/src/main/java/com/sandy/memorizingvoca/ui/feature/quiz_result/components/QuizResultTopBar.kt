package com.sandy.memorizingvoca.ui.feature.quiz_result.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
internal fun QuizResultTopBar(
    title: String?,
    onDeleteClick: () -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(color = MaterialTheme.colorScheme.surface),
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
            text = title ?: "",
            fontFamily = PyeoginGothic,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            letterSpacing = (-0.1).sp,
        )
        Spacer(modifier = modifier.weight(1f))
        TextButton (
            onClick = onDeleteClick,
        ) {
            Text(
                text = "삭제",
                fontFamily = PyeoginGothic,
                color = Pink100,
            )
        }
    }
}

@Composable
@Preview
private fun QuizResultTopBarPreview() {
    QuizResultTopBar(
        title = "Day 01",
        onDeleteClick = {},
        onNavigateBack = {},
    )
}