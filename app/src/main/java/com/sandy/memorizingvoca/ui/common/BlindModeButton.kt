package com.sandy.memorizingvoca.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
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
    val color = if(isBlindMode) Color.Gray else Pink100
    Column(
        modifier = modifier.noRippleClickable(
            onClick = {
                onBlindModeChange(!isBlindMode)
            }
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = if(isBlindMode) Icons.Rounded.VisibilityOff else Icons.Rounded.Visibility,
            contentDescription = null,
            tint = color,
        )
        Text(
            text = if(isBlindMode) "뜻가리기" else "뜻보이기",
            fontWeight = FontWeight.Medium,
            fontSize = 10.sp,
            color = color,
        )
    }
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
