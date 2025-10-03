package com.sandy.memorizingvoca.ui.feature.voca_fullscreen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sandy.memorizingvoca.ui.extensions.noRippleClickable
import com.sandy.memorizingvoca.ui.theme.Gray100
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme

@Composable
internal fun FullScreenPageHeader(
    page: Int,
    totalPage: Int,
    resetButtonEnabled: Boolean,
    onResetClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.padding(
            top = 8.dp,
            start = 24.dp,
            end = 24.dp,
        ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "Page ",
            color = MaterialTheme.colorScheme.onSecondary,
        )
        Spacer(modifier = modifier.width(4.dp))
        Text(
            text = "$page",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
        )
        Text(
            text = " / ",
            color = Color.Gray,
        )
        Text(
            text = "$totalPage",
            fontWeight = FontWeight.Medium,
        )
        Spacer(modifier = modifier.weight(1f))
        Text(
            modifier = modifier.noRippleClickable(
                enabled = resetButtonEnabled,
                onClick = onResetClick,
            ),
            text = "RESET",
            fontWeight = FontWeight.Medium,
            color = if(resetButtonEnabled) MaterialTheme.colorScheme.primary else Gray100,
        )
    }
}

@Preview
@Composable
private fun FullScreenFooterPreview() {
    MemorizingVocaTheme {
        FullScreenPageHeader(
            page = 1,
            totalPage = 6,
            resetButtonEnabled = true,
            onResetClick = {},
        )
    }
}
