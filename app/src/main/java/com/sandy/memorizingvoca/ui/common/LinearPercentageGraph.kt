package com.sandy.memorizingvoca.ui.common

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sandy.memorizingvoca.ui.theme.Gray30
import com.sandy.memorizingvoca.ui.theme.Pink80

@Composable
fun LinearPercentageGraph(
    currentValue: Int,
    maxValue: Int,
    modifier: Modifier = Modifier,
) {
    val animatedProgress by animateFloatAsState(
        targetValue = calculateTargetValue(
            maxValue = maxValue,
            currentValue = currentValue,
        ),
        animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing)
    )
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(25.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val cornerRadius = 50f

            // Draw background bar
            val backgroundBarPath = Path().apply {
                addRoundRect(
                    RoundRect(
                        rect = Rect(Offset.Zero, Size(size.width, size.height)),
                        topLeft = CornerRadius(cornerRadius),
                        topRight = CornerRadius(cornerRadius),
                        bottomLeft = CornerRadius(cornerRadius),
                        bottomRight = CornerRadius(cornerRadius)
                    )
                )
            }

            drawPath(
                path = backgroundBarPath,
                color = Gray30,
            )

            // Clip progress bar to fit within the background bar
            clipPath(backgroundBarPath) {
                // Create progress bar path with rounded left corners
                val progressBarWidth = size.width * animatedProgress
                val progressBarPath = Path().apply {
                    moveTo(0f, 0f) // Top-left corner
                    lineTo(progressBarWidth, 0f) // Top-right corner
                    lineTo(progressBarWidth, size.height) // Bottom-right corner
                    lineTo(0f, size.height) // Bottom-left corner
                    arcTo(
                        rect = Rect(
                            left = 0f,
                            top = 0f,
                            right = cornerRadius,
                            bottom = size.height
                        ),
                        startAngleDegrees = 90f,
                        sweepAngleDegrees = 180f,
                        forceMoveTo = false
                    )
                    close()
                }

                // Draw progress bar
                drawPath(
                    path = progressBarPath,
                    color = Pink80,
                )

            }
        }
    }
}

private fun calculateTargetValue(
    maxValue: Int,
    currentValue: Int,
): Float {
    if(maxValue == 0) return 0f
    return currentValue.toFloat() / maxValue.toFloat()
}

@Preview(name = "LinearExpGraph")
@Composable
private fun PreviewLinearExpGraph() {
    Box(
        modifier = Modifier
            .background(color = Color.Gray)
            .padding(20.dp),
    ) {
        LinearPercentageGraph(
            300,
            400,
        )
    }
}