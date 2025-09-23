package com.sandy.memorizingvoca.ui.feature.voca_list.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
internal fun VocaListTopBar(
    day: Int,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically,) {
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
        TextButton (
            onClick = onNavigateBack,
        ) {
            Text(
                text = "Quiz",
                fontFamily = PyeoginGothic,
                color = Pink100,
            )
        }
        Spacer(modifier = modifier.width(8.dp))
    }
}

@Composable
@Preview
private fun VocaListTopBarPreview() {
    VocaListTopBar(
        day = 1,
        onNavigateBack = {},
    )
}