package com.sandy.memorizingvoca.ui.feature.voca_fullscreen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme
import com.sandy.memorizingvoca.ui.theme.Pink100

@Composable
internal fun FullScreenButtonFooter(
     onPrevButtonClick: () -> Unit,
    onNextButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp,
                bottom = 16.dp,
            ),
    ) {
        TextButton(
            modifier = modifier.align(Alignment.BottomStart),
            onClick = onPrevButtonClick,
        ) {
            Text(
                text = "이전",
                color = Pink100,
            )
        }
        TextButton(
            modifier = modifier.align(Alignment.BottomEnd),
            onClick = onNextButtonClick,
        ) {
            Text(
                text = "다음",
                color = Pink100,
            )
        }
    }
}

@Preview
@Composable
private fun FullScreenButtonFooterPreview() {
    MemorizingVocaTheme {
        FullScreenButtonFooter(
            onPrevButtonClick = {},
            onNextButtonClick = {},
        )
    }
}
