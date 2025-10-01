package com.sandy.memorizingvoca.ui.feature.voca_list.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
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
import com.sandy.memorizingvoca.ui.common.MyTextButton
import com.sandy.memorizingvoca.ui.extensions.noRippleClickable
import com.sandy.memorizingvoca.ui.resources.Visibility
import com.sandy.memorizingvoca.ui.resources.VisibilityOff
import com.sandy.memorizingvoca.ui.theme.Pink100

@Composable
internal fun VocaListTopBar(
    day: Int,
    blindMode: Boolean,
    onBlindModeChange: (Boolean) -> Unit,
    onNavigateBack: () -> Unit,
    onNavigateFullScreen: (Int) -> Unit,
    onNavigateQuiz1: (Int) -> Unit,
    onNavigateQuiz2: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
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
            text = "Day " + String.format("%02d", day),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
        )
        Spacer(modifier = modifier.width(8.dp))
        Icon(
            modifier = modifier.size(20.dp).noRippleClickable {
                onBlindModeChange(!blindMode)
            },
            imageVector = if(blindMode) Icons.Rounded.VisibilityOff else Icons.Rounded.Visibility,
            contentDescription = null,
            tint = if(blindMode) Color.Gray else Pink100,
        )
        Spacer(
            modifier = modifier.weight(1f),
        )
        MyTextButton(
            title = "Full",
            onClick = {
                onNavigateFullScreen(day)
            },
        )
        MyTextButton(
            title = "Quiz1",
            onClick = {
                onNavigateQuiz1(day)
            },
        )
        MyTextButton(
            title = "Quiz2",
            onClick = {
                onNavigateQuiz2(day)
            },
        )
        Spacer(modifier = modifier.width(8.dp))
    }
}

@Composable
@Preview
private fun VocaListTopBarPreview() {
    VocaListTopBar(
        day = 1,
        blindMode = false,
        onBlindModeChange = {},
        onNavigateBack = {},
        onNavigateFullScreen = {},
        onNavigateQuiz1 = {},
        onNavigateQuiz2 = {}
    )
}