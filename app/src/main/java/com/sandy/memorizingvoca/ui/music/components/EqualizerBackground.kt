package com.sandy.memorizingvoca.ui.music.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sandy.memorizingvoca.ui.theme.Pink40
import kotlin.random.Random

@Composable
internal fun EqualizerBackground(
    barWidth: Dp = 5.dp,       // 막대 너비
    barSpacing: Dp = 4.dp,     // 막대 간격
    barColor: Color = Pink40,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(
        modifier = modifier.fillMaxSize()
    ) {
        val barCount = (maxWidth / (barWidth + barSpacing)).toInt()
        val animatables = remember {
            List(barCount) { Animatable(0f) }
        }

        // 각 바 애니메이션 실행
        animatables.forEach { animatable ->
            LaunchedEffect(animatable) {
                while (true) {
                    animatable.animateTo(
                        Random.nextFloat(), // 0f ~ 1f
                        animationSpec = tween(
                            durationMillis = Random.nextInt(100, 300),
                            easing = LinearEasing
                        )
                    )
                }
            }
        }

        Canvas(modifier = Modifier.fillMaxSize()) {
            val barW = barWidth.toPx()
            val spacing = barSpacing.toPx()
            val maxH = size.height

            for (i in 0 until barCount) {
                val value = animatables[i].value
                val barHeight = maxH * value
                val left = i * (barW + spacing)
                val top = maxH - barHeight

                drawRect(
                    color = barColor.copy(alpha = 0.8f),
                    topLeft = Offset(left, top),
                    size = Size(barW, barHeight)
                )
            }
        }
    }
}