package com.sandy.memorizingvoca.ui.feature.quiz_result.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sandy.memorizingvoca.ui.common.LinearPercentageGraph
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme

@Composable
internal fun QuizResultScoreView(
    date: String?,
    correctCount: Int?,
    totalCount: Int?,
    percentage: Int?,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(horizontal = 20.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if(totalCount != null && correctCount != null) {
                ScoreView(
                    correctCount = correctCount,
                    total = totalCount,
                )
            }
            Spacer(modifier = modifier.weight(1f))
            Text(
                text = date ?: "",
                color = Color.Gray,
            )
        }
        Spacer(modifier = modifier.height(12.dp))
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            LinearPercentageGraph(
                modifier = modifier.fillMaxWidth(0.8f),
                currentValue = correctCount ?: 0,
                maxValue = totalCount ?: 0,
            )
            Text(
                modifier = modifier.fillMaxWidth(),
                text = "${percentage ?: 0}%",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                textAlign = TextAlign.End,
            )
        }
        Spacer(modifier = modifier.height(16.dp))
    }
}

@Composable
private fun ScoreView(
    correctCount: Int,
    total: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "정답률",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSecondary,
        )
        Spacer(modifier = modifier.width(8.dp))
        Text(
            text = "$correctCount",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.primary,
        )
        Text(
            text = " / ",
            fontSize = 16.sp,
            color = Color.Gray,
        )
        Text(
            text = "$total",
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
        )
    }
}

@Preview
@Composable
private fun QuizResultGraphViewPreview() {
    MemorizingVocaTheme {
        QuizResultScoreView(
            correctCount = 10,
            totalCount = 20,
            percentage = 50,
            date = "2025년 09월 24일 18:45",
        )
    }
}
