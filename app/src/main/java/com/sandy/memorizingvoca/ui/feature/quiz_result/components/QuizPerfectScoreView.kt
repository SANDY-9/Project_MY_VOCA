package com.sandy.memorizingvoca.ui.feature.quiz_result.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sandy.memorizingvoca.R
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme
import com.sandy.memorizingvoca.ui.theme.Pink40

@Composable
internal fun QuizPerfectScoreView(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = modifier.weight(0.4f))
        Icon(
            painter = painterResource(R.drawable.my_voca),
            contentDescription = null,
            tint = Pink40,
        )
        Text(
            text = "Perfect Score!",
            color = Color.Gray,
        )
        Spacer(modifier = modifier.height(8.dp))
        Text(
            text = "틀린 문제가 없습니다.",
            color = Color.Gray,
        )
        Spacer(modifier = modifier.weight(1f))
    }
}

@Preview
@Composable
private fun QuizPerfectScoreViewPreview() {
    MemorizingVocaTheme {
        QuizPerfectScoreView(
        )
    }
}
