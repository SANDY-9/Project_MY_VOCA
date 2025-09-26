package com.sandy.memorizingvoca.ui.feature.voca_fullscreen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.sandy.memorizingvoca.data.model.Vocabulary
import com.sandy.memorizingvoca.ui.extensions.noRippleClickable
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme
import com.sandy.memorizingvoca.ui.theme.PyeoginGothic

@Composable
internal fun FullScreenVocaPager(
    pagerState: PagerState,
    vocaList: List<Vocabulary>,
    blindMode: Boolean,
    onVocaSpeak: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    HorizontalPager(
        modifier = modifier.fillMaxSize(),
        state = pagerState,
    ) { index ->
        val item = vocaList[index]
        FullScreenVocaView(
            word = item.word,
            meaning = item.meaning,
            blindMode = blindMode,
            onSpeak = {
                onVocaSpeak(item.word)
            },
        )
    }
}

@Composable
private fun FullScreenVocaView(
    word: String,
    meaning: String,
    blindMode: Boolean,
    onSpeak: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var meaningVisible by remember { mutableStateOf(!blindMode) }
    LaunchedEffect(blindMode) {
        meaningVisible = !blindMode
    }
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = modifier.weight(0.9f))
        Box(
            modifier = modifier.fillMaxWidth(0.9f),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                modifier = modifier.noRippleClickable(onClick = onSpeak),
                text = word,
                fontWeight = FontWeight.Black,
                fontSize = 45.sp,
                textAlign = TextAlign.Center,
                style = TextStyle.Default.copy(
                    lineBreak = LineBreak.Heading,
                    fontFamily = PyeoginGothic,
                ),
            )
        }
        Spacer(modifier = modifier.fillMaxHeight(0.05f))
        Box(
            modifier = modifier
                .weight(1f)
                .alpha(
                    if (meaningVisible) 1f else 0f
                )
                .noRippleClickable {
                    meaningVisible = !meaningVisible
                },
        ) {
            Text(
                modifier = modifier.fillMaxWidth(0.9f),
                text = meaning,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                lineHeight = 27.sp,
                style = TextStyle.Default.copy(
                    lineBreak = LineBreak.Heading,
                    fontFamily = PyeoginGothic,
                ),
            )
        }
    }
}

@Preview
@Composable
private fun FullScreenVocaPagerPreview() {
    MemorizingVocaTheme {
        FullScreenVocaPager(
            pagerState = rememberPagerState { 3 },
            vocaList = listOf(
                Vocabulary(
                    vocaId = 1,
                    day = 1,
                    word = "ascribe",
                    meaning = "[동] -의 탓으로 돌리다"
                ),
                Vocabulary(
                    vocaId = 4,
                    day = 1,
                    word = "dictate",
                    meaning = "[동] ① 명령하다, 지시하다 ② 받아쓰게 하다, 구술하다 [명] 명령, 지시[주로 pl.]",
                ),
                Vocabulary(
                    vocaId = 5,
                    day = 1,
                    word = "have trouble[difficulty] (in)",
                    meaning = "ing 하는 데 어려움[곤란]을 겪다"
                )
            ),
            blindMode = false,
            onVocaSpeak = {},
        )
    }
}
