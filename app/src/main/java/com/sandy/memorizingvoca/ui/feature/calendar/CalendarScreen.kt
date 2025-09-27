package com.sandy.memorizingvoca.ui.feature.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sandy.memorizingvoca.ui.feature.calendar.components.CalendarTopBar
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme

@Composable
internal fun CalendarRoute(
    navigateQuizResult: (String) -> Unit,
) {
    CalendarScreen(
        onAllQuizClear = {},
    )
}

@Composable
private fun CalendarScreen(
    onAllQuizClear: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        CalendarTopBar(
            onAllQuizClearClick = onAllQuizClear,
        )
    }
}

@Preview
@Composable
private fun CalendarScreenPreview() {
    MemorizingVocaTheme {
        CalendarScreen(
            onAllQuizClear = {},
        )
    }
}
