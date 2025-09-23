package com.sandy.memorizingvoca.ui.feature.voca_list

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sandy.memorizingvoca.data.model.Vocabulary
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme

@Composable
internal fun VocaListRoute(
    onItemClick: (Int) -> Unit,
    viewModel: VocaListViewModel = hiltViewModel(),
) {
    val vocaList by viewModel.vocaList.collectAsStateWithLifecycle()
    VocaListScreen(
        day = viewModel.day,
        vocaList = vocaList,
        onItemClick = onItemClick,
    )
}

@Composable
private fun VocaListScreen(
    day: Int,
    vocaList: List<Vocabulary>,
    onItemClick: (Int) -> Unit,
) {
    Text(
        text = "$vocaList"
    )
}

@Composable
@Preview
private fun VocaListScreenPreview() {
    MemorizingVocaTheme {
        VocaListScreen(
            day = 1,
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
                    meaning = "[동] ① 지지하다, 옹호하다 ② 주장하다 [명] 지지자, 옹호자"
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
            onItemClick = {}
        )
    }
}