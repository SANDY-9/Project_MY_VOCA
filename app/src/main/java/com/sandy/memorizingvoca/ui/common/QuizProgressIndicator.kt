package com.sandy.memorizingvoca.ui.common

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sandy.memorizingvoca.ui.theme.Gray30

@Composable
fun QuizProgressIndicator(
    progressed: Boolean,
    durationMillis: Int,
    modifier: Modifier = Modifier,
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val density = LocalDensity.current
    val animWidth = remember { Animatable(0f) } // px 단위로 애니메이션 관리

    LaunchedEffect(progressed) {
        animWidth.run {
            if(progressed) {
                stop()
                snapTo(0f)
                animateTo(
                    targetValue = with(density) { screenWidth.toPx() },
                    animationSpec = tween(
                        durationMillis = durationMillis,
                        easing = FastOutLinearInEasing,
                    )
                )
            }
            else {
                snapTo(0f)
            }
        }
    }

    Box(
        modifier = modifier
            .height(8.dp)
            .width(with(density) { animWidth.value.toDp() })
            .background(color = Gray30)
    )
}


@Composable
@Preview
private fun PreviewQuizProgressIndicator() {
    var progressed by remember { mutableStateOf(true) }
    Column (modifier = Modifier.fillMaxWidth()) {
        QuizProgressIndicator(
            progressed = progressed,
            durationMillis = 1000,
        )
        Button(
            onClick = {
                progressed = !progressed
            }
        ) {
            Text(
                text = "애니메이션 상태 조절 : $progressed"
            )
        }
    }
}
