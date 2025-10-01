package com.sandy.memorizingvoca.ui.feature.voca_details.components

import androidx.compose.foundation.background
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sandy.memorizingvoca.data.model.Vocabulary
import com.sandy.memorizingvoca.ui.common.MyNavigationButton
import com.sandy.memorizingvoca.ui.common.VocaBookmarkButton
import com.sandy.memorizingvoca.ui.common.VocaHighlightButton

@Composable
internal fun VocaDetailsTopBar(
    voca: Vocabulary?,
    onNavigateBack: () -> Unit,
    onHighlightChange: (Boolean) -> Unit,
    onBookmarkChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val title = if(voca?.day != null) "Day " + String.format("%02d", voca.day) else ""
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(end = 16.dp),
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
        VocaHighlightButton(
            highlighted = voca?.highlighted ?: false,
            onHighlightChange = onHighlightChange,
        )
        Spacer(modifier = modifier.width(8.dp))
        VocaBookmarkButton(
            bookmarked = voca?.bookmarked ?: false,
            onBookmarkChange = onBookmarkChange,
        )
    }
}

@Composable
@Preview
private fun VocaListTopBarPreview() {
    VocaDetailsTopBar(
        voca = Vocabulary(
            vocaId = 5,
            day = 1,
            word = "have trouble[difficulty] (in)",
            meaning = "ing 하는 데 어려움[곤란]을 겪다"
        ),
        onNavigateBack = {},
        onHighlightChange = {},
        onBookmarkChange = {},
    )
}