package com.sandy.memorizingvoca.ui.feature.quiz_result.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sandy.memorizingvoca.ui.common.MyNavigationButton
import com.sandy.memorizingvoca.ui.common.MyTextButton

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
        MyNavigationButton(
            onNavigateBack = onNavigateBack,
        )
        Text(
            modifier = modifier.weight(1f),
            text = title ?: "",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
        )
        Spacer(modifier = modifier.weight(1f))
        MyTextButton(
            title = "삭제",
            onClick = onDeleteClick,
        )
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