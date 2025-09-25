package com.sandy.memorizingvoca.ui.common

import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.sandy.memorizingvoca.ui.extensions.noRippleClickable
import com.sandy.memorizingvoca.ui.resources.Visibility
import com.sandy.memorizingvoca.ui.resources.VisibilityOff
import com.sandy.memorizingvoca.ui.theme.Pink100

@Composable
fun VocaBlindModeButton(
    isBlindMode: Boolean,
    onBlindModeChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Icon(
        modifier = modifier.noRippleClickable {
            onBlindModeChange(!isBlindMode)
        },
        imageVector = if(isBlindMode) Icons.Rounded.VisibilityOff else Icons.Rounded.Visibility,
        contentDescription = null,
        tint = if(isBlindMode) Color.LightGray else Pink100,
    )
}

@Preview
@Composable
private fun VocaBlindModeButtonPreview() {
    var isBlindMode by remember { mutableStateOf(false) }
    VocaBlindModeButton(
        isBlindMode = isBlindMode,
        onBlindModeChange = { isBlindMode = !isBlindMode }
    )
}
