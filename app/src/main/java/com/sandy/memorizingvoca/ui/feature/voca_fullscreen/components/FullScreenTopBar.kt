package com.sandy.memorizingvoca.ui.feature.voca_fullscreen.components

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sandy.memorizingvoca.ui.common.VocaAutoModeButton
import com.sandy.memorizingvoca.ui.common.VocaBlindModeButton
import com.sandy.memorizingvoca.ui.theme.PyeoginGothic

@Composable
internal fun FullScreenTopBar(
    title: String,
    blindMode: Boolean,
    autoMode: Boolean,
    onBlindModeChange: (Boolean) -> Unit,
    onAutoModeChange: (Boolean) -> Unit,
    onNavigateBack: () -> Unit,
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
            modifier = modifier.weight(1f),
            text = title,
            fontFamily = PyeoginGothic,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            letterSpacing = (-0.1).sp,
        )
        VocaBlindModeButton(
            isBlindMode = blindMode,
            onBlindModeChange = onBlindModeChange,
        )
        Spacer(modifier = modifier.width(16.dp))
        VocaAutoModeButton(
            isAutoMode = autoMode,
            onAutoModeChange = onAutoModeChange,
        )
        Spacer(modifier = modifier.width(16.dp))
    }
}

@Composable
@Preview
private fun FullScreenTopBarPreview() {
    FullScreenTopBar(
        title = "Day 01",
        blindMode = false,
        autoMode = false,
        onBlindModeChange = {},
        onAutoModeChange = {},
        onNavigateBack = {},
    )
}