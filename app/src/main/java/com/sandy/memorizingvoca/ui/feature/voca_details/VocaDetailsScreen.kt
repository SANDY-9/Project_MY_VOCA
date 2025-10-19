package com.sandy.memorizingvoca.ui.feature.voca_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sandy.memorizingvoca.data.model.Vocabulary
import com.sandy.memorizingvoca.data.model.VocabularyDetails
import com.sandy.memorizingvoca.data.model.Word
import com.sandy.memorizingvoca.ui.feature.voca_details.components.VocaDetailsTitleView
import com.sandy.memorizingvoca.ui.feature.voca_details.components.VocaDetailsTopBar
import com.sandy.memorizingvoca.ui.feature.voca_details.components.VocaExampleView
import com.sandy.memorizingvoca.ui.feature.voca_details.components.VocaFamilyView
import com.sandy.memorizingvoca.ui.feature.voca_details.components.VocaGrammarView
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme

@Composable
internal fun VocaDetailsRoute(
    onNavigateBack: () -> Unit,
    viewModel: VocaDetailsViewModel = hiltViewModel(),
) {
    val voca by viewModel.voca.collectAsStateWithLifecycle()
    val vocaDetailsState by viewModel.detailsState.collectAsStateWithLifecycle()
    VocaDetailsScreen(
        voca = voca,
        vocaDetailsState = vocaDetailsState,
        onNavigateBack = onNavigateBack,
        onHighlightChange = viewModel::updateHighlight,
        onBookmarkChange = viewModel::updateBookmark,
    )
}
@Composable
private fun VocaDetailsScreen(
    voca: Vocabulary?,
    vocaDetailsState: VocaDetailsState,
    onNavigateBack: () -> Unit,
    onHighlightChange: (Boolean) -> Unit,
    onBookmarkChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        VocaDetailsTopBar(
            voca = voca,
            onNavigateBack = onNavigateBack,
            onHighlightChange = onHighlightChange,
            onBookmarkChange = onBookmarkChange,
        )
        LazyColumn (
            modifier = modifier.fillMaxWidth(),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            stickyHeader {
                VocaDetailsTitleView(
                    word = voca?.word ?: "",
                    meaning = voca?.meaning ?: "",
                    highlighted = voca?.highlighted ?: false,
                    pron = voca?.pron,
                )
            }
            if(vocaDetailsState is VocaDetailsState.Success) {
                vocaDetailsState.details?.run {
                    item {
                        VocaGrammarView(
                            item = grammar,
                        )
                        VocaFamilyView(
                            title = "파생어",
                            item = wordFamily,
                        )
                        VocaFamilyView(
                            title = "복합어・숙어",
                            item = includeWord,
                        )
                        VocaFamilyView(
                            title = "반의어",
                            item = oppositeWord,
                        )
                        VocaFamilyView(
                            title = "유의어",
                            item = similarWord,
                        )
                        VocaExampleView(
                            item = exampleList,
                        )
                        SourcesText()
                    }
                }
            }
        }
        if(vocaDetailsState == VocaDetailsState.Loading) {
            VocaDetailsLoadingView(
                modifier = modifier
                    .fillMaxWidth()
                    .weight(1f),
            )
        }
        if(vocaDetailsState == VocaDetailsState.Fail) {
            VocaDetailsFailView(
                modifier = modifier
                    .fillMaxWidth()
                    .weight(1f),
            )
        }
    }
}

@Composable
private fun SourcesText(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.padding(16.dp),
    ) {
        Text(
            text = "출처 : ",
            color = Color.Gray,
        )
        Text(
            text = buildAnnotatedString {
                withLink(
                    LinkAnnotation.Url(
                        "https://dic.daum.net/index.do?dic=eng",
                        TextLinkStyles(
                            style = SpanStyle(
                                color = Color(0xFF273552),
                                fontWeight = FontWeight.Bold,
                            )
                        )
                    )
                ) {
                    append("다음 영어사전")
                }
            },
        )
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
            vocaDetailsState = VocaDetailsState.Success(
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
                    includeWord = emptyList(),
                ),
            ),
            onNavigateBack = {},
            onHighlightChange = {},
            onBookmarkChange = {},
        )
    }
}