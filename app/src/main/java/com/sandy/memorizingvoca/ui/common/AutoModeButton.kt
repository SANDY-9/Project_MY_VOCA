package com.sandy.memorizingvoca.ui.common

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sandy.memorizingvoca.ui.extensions.noRippleClickable
import com.sandy.memorizingvoca.ui.resources.AutoMode
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme
import com.sandy.memorizingvoca.ui.theme.Pink100

@Composable
fun VocaAutoModeButton(
    isAutoMode: Boolean,
    onAutoModeChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val rotation = remember { Animatable(0f) }
    val color = if(isAutoMode) Pink100 else Color.Gray

    LaunchedEffect(isAutoMode) {
        if (isAutoMode) {
            rotation.animateTo(
                targetValue = 360f,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = 2000, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                )
            )
        } else {
            rotation.stop()
            rotation.snapTo(0f)
        }
    }

    Column(
        modifier = modifier.noRippleClickable(
            onClick = {
                onAutoModeChange(!isAutoMode)
            }
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            modifier = modifier.rotate(rotation.value).size(22.dp),
            imageVector = Icons.Rounded.AutoMode,
            contentDescription = null,
            tint = color,
        )
        Spacer(modifier = modifier.height(2.dp))
        Text(
            text = "AUTO",
            fontWeight = FontWeight.Medium,
            fontSize = 10.sp,
            color = color,
        )
    }
}

@Preview
@Composable
private fun VocaAutoModeButtonPreview() {
    MemorizingVocaTheme {
        var isAutoMode by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier.height(56.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            VocaAutoModeButton(
                isAutoMode = isAutoMode,
                onAutoModeChange = { isAutoMode = !isAutoMode }
            )
        }

    }
}
