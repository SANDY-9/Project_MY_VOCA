package com.sandy.memorizingvoca.ui.feature.voca_details.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sandy.memorizingvoca.ui.common.VocaBookmarkButton
import com.sandy.memorizingvoca.ui.common.VocaHighlightButton
import com.sandy.memorizingvoca.ui.theme.PyeoginGothic

@Composable
internal fun VocaDetailsTopBar(
    day: Int,
    onNavigateBack: () -> Unit,
    onHighLightChange: () -> Unit,
    onBookmarkChange: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(end = 16.dp),
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
            text = "Day " + String.format("%02d", day),
            fontFamily = PyeoginGothic,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            letterSpacing = (-0.1).sp,
        )
        VocaHighlightButton(
            highlighted = false,
            onHighlightChange = {},
        )
        Spacer(modifier = modifier.width(8.dp))
        VocaBookmarkButton(
            bookmarked = false,
            onBookmarkChange = {},
        )
    }
}

@Composable
@Preview
private fun VocaListTopBarPreview() {
    VocaDetailsTopBar(
        day = 1,
        onNavigateBack = {},
        onHighLightChange = {},
        onBookmarkChange = {},
    )
}