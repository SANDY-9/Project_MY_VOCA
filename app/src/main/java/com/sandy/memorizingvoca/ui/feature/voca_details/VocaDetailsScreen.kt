package com.sandy.memorizingvoca.ui.feature.voca_details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sandy.memorizingvoca.data.model.Vocabulary
import com.sandy.memorizingvoca.data.model.VocabularyDetails
import com.sandy.memorizingvoca.data.model.Word
import com.sandy.memorizingvoca.ui.feature.voca_details.components.VocaDetailsTitleView
import com.sandy.memorizingvoca.ui.feature.voca_details.components.VocaDetailsTopBar
import com.sandy.memorizingvoca.ui.feature.voca_details.components.VocaFamilyView
import com.sandy.memorizingvoca.ui.feature.voca_details.components.VocaGrammarView
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme

@Composable
internal fun VocaDetailsRoute(
    onNavigateBack: () -> Unit,
    viewModel: VocaDetailsViewModel = hiltViewModel(),
) {
    val voca by viewModel.voca.collectAsStateWithLifecycle()
    val vocaDetails by viewModel.details.collectAsStateWithLifecycle()
    VocaDetailsScreen(
        voca = voca,
        details = vocaDetails,
        onNavigateBack = onNavigateBack,
        onHighlightChange = viewModel::updateHighlight,
        onBookmarkChange = viewModel::updateBookmark,
    )
}
@Composable
private fun VocaDetailsScreen(
    voca: Vocabulary?,
    details: VocabularyDetails?,
    onNavigateBack: () -> Unit,
    onHighlightChange: (Boolean) -> Unit,
    onBookmarkChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn (
        modifier = modifier.fillMaxSize(),
    ) {
        stickyHeader {
            VocaDetailsTopBar(
                voca = voca,
                onNavigateBack = onNavigateBack,
                onHighlightChange = onHighlightChange,
                onBookmarkChange = onBookmarkChange,
            )
            VocaDetailsTitleView(
                word = voca?.word ?: "",
                meaning = voca?.meaning ?: "",
                highlighted = voca?.highlighted ?: false,
            )
        }
        item {
            VocaGrammarView(
                item = details?.grammar ?: emptyMap(),
            )
            VocaFamilyView(
                title = "파생어",
                item = details?.wordFamily ?: emptyList(),
            )
            VocaFamilyView(
                title = "반의어",
                item = details?.oppositeWord ?: emptyList(),
            )
            VocaFamilyView(
                title = "유의어",
                item = details?.similarWord ?: emptyList(),
            )
        }
    }

}

@Composable
@Preview
private fun VocaDetailsScreenPreview() {
    MemorizingVocaTheme {
        VocaDetailsScreen(
            voca = Vocabulary(
                vocaId = 4,
                day = 1,
                word = "dictate",
                meaning = "[동] ① 명령하다, 지시하다 ② 받아쓰게 하다, 구술하다 [명] 명령, 지시[주로 pl.]",
            ),
            details = VocabularyDetails(
                word = "dictate",
                grammar = mapOf(
                    "동사" to listOf(
                        Word(
                            word = "earnest",
                            mean = "기본"
                        ),
                        Word(
                            word = "earnested",
                            mean = "과거"
                        ),
                        Word(
                            word = "earnested",
                            mean = "과거분사"
                        ),
                        Word(
                            word = "earnesting",
                            mean = "현재분사"
                        ),
                        Word(
                            word = "earnests",
                            mean = "3인칭단수"
                        ),
                    ),
                    "명사" to listOf(
                        Word(
                            word = "earnest",
                            mean = "기본"
                        ),
                    ),
                    "형용사" to listOf(
                        Word(
                            word = "earnest",
                            mean = "기본"
                        ),
                        Word(
                            word = "more earnest, earnester",
                            mean = "비교급"
                        ),
                        Word(
                            word = "most earnest, earnestest",
                            mean = "최상급"
                        ),
                    )
                ),
                wordFamily = listOf(
                    Word(
                        word = "withdrawal",
                        mean = "탈퇴, 철수, 철회, 인출, 금단 증상"
                    ),
                    Word(
                        word = "serious",
                        mean = "심각한, 진지한, 정말, 중대한, 어려운"
                    ),
                ),
                similarWord = listOf(
                    Word(
                        word = "withdrawal",
                        mean = "탈퇴, 철수, 철회, 인출, 금단 증상"
                    ),
                    Word(
                        word = "serious",
                        mean = "심각한, 진지한, 정말, 중대한, 어려운"
                    ),
                ),
                oppositeWord = listOf(
                    Word(
                        word = "withdrawal",
                        mean = "탈퇴, 철수, 철회, 인출, 금단 증상"
                    ),
                    Word(
                        word = "serious",
                        mean = "심각한, 진지한, 정말, 중대한, 어려운"
                    ),
                ),
                exampleList = emptyList(),
            ),
            onNavigateBack = {},
            onHighlightChange = {},
            onBookmarkChange = {},
        )
    }
}