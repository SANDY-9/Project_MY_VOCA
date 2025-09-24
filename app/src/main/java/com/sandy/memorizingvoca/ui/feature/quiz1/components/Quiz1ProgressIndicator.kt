package com.sandy.memorizingvoca.ui.feature.quiz1.components

import android.media.VolumeShaper
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sandy.memorizingvoca.ui.theme.Gray30

@Composable
internal fun Quiz1ProgressIndicator(
    progressed: Boolean,
    modifier: Modifier = Modifier,
    durationMillis: Int = 5000,
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    var targetWidth by remember { mutableStateOf(0.dp) }
    val animatedWidth by animateDpAsState(
        targetValue = targetWidth,
        animationSpec = tween(
            durationMillis = durationMillis,
            easing = FastOutLinearInEasing,
        ),
    )

    LaunchedEffect(Unit) {
        targetWidth = screenWidth
    }

    val progressColor = if(progressed) Gray30 else Color.Transparent
    Box(
        modifier = modifier
            .height(8.dp)
            .width(animatedWidth)
            .background(color = progressColor)
    )
}


@Composable
@Preview
private fun PreviewQuiz1ProgressIndicator() {
    Box(modifier = Modifier.fillMaxWidth()) {
        Quiz1ProgressIndicator(progressed = true)
    }
}
