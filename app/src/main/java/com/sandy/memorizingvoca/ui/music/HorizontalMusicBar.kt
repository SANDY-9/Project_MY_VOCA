package com.sandy.memorizingvoca.ui.music

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.sandy.memorizingvoca.ui.theme.Gray30
import com.sandy.memorizingvoca.ui.theme.Gray50
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme
import com.sandy.memorizingvoca.ui.theme.Pink100
import com.sandy.memorizingvoca.ui.theme.Pink40

@Composable
internal fun HorizontalMusicBar(
    value: Float,                          // 0f..1f
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    barColor: Color = Pink40,
    trackHeight: Float = 4f,               // dp 단위 대신 Canvas에 px로 사용하므로 변환 필요하면 조정
) {
    // 내부에서 사용할 width(픽셀)
    var sizePx by remember { mutableStateOf(IntSize(0, 0)) }

    // 애니메이션된 표시값 (value가 변할 때 부드럽게 변경)
    val animated = animateFloatAsState(targetValue = value.coerceIn(0f, 1f)).value

    Box(
        modifier = modifier
            .height(4.dp)
            .fillMaxWidth()
            .onSizeChanged { sizePx = it }
            // 탭으로 값 변경
            .pointerInput(Unit) {
                detectTapGestures { offset ->
                    if (sizePx.width > 0) {
                        val fraction = (offset.x / sizePx.width.toFloat()).coerceIn(0f, 1f)
                        onValueChange(fraction)
                    }
                }
            }
            // 드래그로 값 변경
            .pointerInput(Unit) {
                detectDragGestures { change, _ ->
                    val posX = change.position.x
                    if (sizePx.width > 0) {
                        val fraction = (posX / sizePx.width.toFloat()).coerceIn(0f, 1f)
                        onValueChange(fraction)
                    }
                }
            }
    ) {
        // Canvas로 트랙과 채움, 썸(원)을 그림
        Canvas(modifier = Modifier.fillMaxSize()) {
            val trackY = size.height / 2f
            val trackStroke = trackHeight * density  // 트랙 높이를 px로 환산
            // 배경 트랙
            drawLine(
                color = Gray30,
                start = Offset(0f, trackY),
                end = Offset(size.width, trackY),
                strokeWidth = trackStroke,
                cap = StrokeCap.Round
            )
            // 채워진 트랙
            val filledEndX = size.width * animated
            drawLine(
                color = barColor,
                start = Offset(0f, trackY),
                end = Offset(filledEndX, trackY),
                strokeWidth = trackStroke,
                cap = StrokeCap.Round
            )
        }
    }
}

@Preview
@Composable
private fun HorizontalMusicBarPreview() {
    var value by remember { mutableStateOf(0.75f) }
    MemorizingVocaTheme {
        HorizontalMusicBar(
            value = value,
            onValueChange = { value = it },
        )
    }
}
