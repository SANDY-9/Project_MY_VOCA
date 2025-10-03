package com.sandy.memorizingvoca.ui.feature.voca_fullscreen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sandy.memorizingvoca.ui.theme.Gray100
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme

@Composable
internal fun FullScreenButtonFooter(
    prevButtonEnabled: Boolean,
    nextButtonEnabled: Boolean,
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
                bottom = 8.dp,
            ),
    ) {
        TextButton(
            modifier = modifier.align(Alignment.BottomStart),
            enabled = prevButtonEnabled,
            colors = ButtonDefaults.textButtonColors(
                contentColor = MaterialTheme.colorScheme.primary,
                disabledContentColor = Gray100,
            ),
            onClick = onPrevButtonClick,
        ) {
            Text(
                text = "이전",
                fontWeight = FontWeight.Medium,
            )
        }
        TextButton(
            modifier = modifier.align(Alignment.BottomEnd),
            enabled = nextButtonEnabled,
            colors = ButtonDefaults.textButtonColors(
                contentColor = MaterialTheme.colorScheme.primary,
                disabledContentColor = Gray100,
            ),
            onClick = onNextButtonClick,
        ) {
            Text(
                text = "다음",
                fontWeight = FontWeight.Medium,
            )
        }
    }
}

@Preview
@Composable
private fun FullScreenButtonFooterPreview() {
    MemorizingVocaTheme {
        FullScreenButtonFooter(
            prevButtonEnabled = true,
            nextButtonEnabled = true,
            onPrevButtonClick = {},
            onNextButtonClick = {},
        )
    }
}
