package com.sandy.memorizingvoca.ui.feature.voca_list.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sandy.memorizingvoca.data.model.Vocabulary
import com.sandy.memorizingvoca.ui.common.VocaSimpleListCard
import com.sandy.memorizingvoca.utils.rememberTTSManager

@Composable
internal fun VocaListView(
    vocaList: List<Vocabulary>,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val ttsManager = rememberTTSManager()
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        items(vocaList) { voca ->
            VocaSimpleListCard(
                word = voca.word,
                meaning = voca.meaning,
                highlighted = voca.highlighted,
                onClick = { onItemClick(voca.vocaId) },
                onSpeak = {
                    ttsManager.speak(voca.word)
                }
            )
        }
    }
}

@Composable
@Preview
private fun VocaListViewPreview() {
    VocaListView(
        vocaList = listOf(
            Vocabulary(
                vocaId = 1,
                day = 1,
                word = "ascribe",
                meaning = "[동] -의 탓으로 돌리다"
            ),
            Vocabulary(
                vocaId = 2,
                day = 1,
                word = "advocate",
                meaning = "[동] ① 지지하다, 옹호하다 ② 주장하다 [명] 지지자, 옹호자",
                highlighted = true,
            ),
            Vocabulary(
                vocaId = 3,
                day = 1,
                word = "affluent",
                meaning = "[형] ① 풍부한 ② 부유한"
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
        onItemClick = {},
    )
}