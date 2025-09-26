package com.sandy.memorizingvoca.ui.feature.voca_fullscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sandy.memorizingvoca.data.model.Vocabulary
import com.sandy.memorizingvoca.ui.feature.voca_fullscreen.components.FullScreenButtonFooter
import com.sandy.memorizingvoca.ui.feature.voca_fullscreen.components.FullScreenPageHeader
import com.sandy.memorizingvoca.ui.feature.voca_fullscreen.components.FullScreenTopBar
import com.sandy.memorizingvoca.ui.feature.voca_fullscreen.components.FullScreenVocaPager
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme
import com.sandy.memorizingvoca.utils.rememberTTSManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@Composable
internal fun VocaFullScreenRoute(
    onNavigateBack: () -> Unit,
    viewModel: VocaFullScreenViewModel = hiltViewModel(),
) {
    val state by viewModel.fullScreenState.collectAsStateWithLifecycle()
    VocaFullScreen(
        title = state.title,
        blindMode = state.blindMode,
        autoMode = state.autoMode,
        page = state.currentPage,
        settledPage = state.settledPage,
        totalPage = state.totalPage,
        vocaList = state.vocaList,
        onBlindModeChange = viewModel::onBlindModeChange,
        onAutoModeChange = viewModel::onAutoModeChange,
        onPageChange = viewModel::onPageChange,
        onSettledPageChange = viewModel::onSettledPageChange,
        onNavigateBack = onNavigateBack,
    )
}

@Composable
private fun VocaFullScreen(
    title: String,
    blindMode: Boolean,
    autoMode: Boolean,
    page: Int,
    settledPage: Int,
    totalPage: Int,
    vocaList: List<Vocabulary>,
    onBlindModeChange: (Boolean) -> Unit,
    onAutoModeChange: (Boolean) -> Unit,
    onPageChange: (Int) -> Unit,
    onSettledPageChange: (Int) -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { totalPage },
    )
    val ttsManager = rememberTTSManager()
    LaunchedEffect(autoMode, settledPage) {
        if(autoMode) {
            val word = vocaList[settledPage -1].word
            var count = 1
            ttsManager.speak(word)
            ttsManager.onDone {
                if(count == 1) {
                    delay(1000L)
                    count ++
                    ttsManager.speak(word)
                    return@onDone
                }
                if(settledPage == totalPage) {
                delay(1000L)
                    onAutoModeChange(false)
                    return@onDone
                }
                pagerState.animateScrollToPage(settledPage)
            }
        }
        if(!autoMode) {
            ttsManager.stop()
        }
    }
    LaunchedEffect(pagerState) {
        snapshotFlow {
            pagerState.currentPage
        }.distinctUntilChanged()
            .collectLatest { index ->
                onPageChange(index)
            }
    }
    LaunchedEffect(pagerState) {
        snapshotFlow {
            pagerState.settledPage
        }.distinctUntilChanged()
            .collectLatest { index ->
                onSettledPageChange(index)
            }
    }
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        FullScreenTopBar(
            title = title,
            blindMode = blindMode,
            autoMode = autoMode,
            onBlindModeChange = onBlindModeChange,
            onAutoModeChange = onAutoModeChange,
            onNavigateBack = onNavigateBack,
        )
        FullScreenPageHeader(
            page = page,
            totalPage = totalPage,
        )
        Box(
            modifier = modifier.weight(1f),
        ) {
            FullScreenVocaPager(
                pagerState = pagerState,
                vocaList = vocaList,
                blindMode = blindMode,
                onVocaSpeak = { word ->
                    ttsManager.speak(word)
                },
            )
        }
        val scope = rememberCoroutineScope()
        FullScreenButtonFooter(
            prevButtonEnabled = settledPage > 1,
            nextButtonEnabled = settledPage < totalPage,
            onPrevButtonClick = {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                }
            },
            onNextButtonClick = {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            },
        )
    }

}

@Preview
@Composable
private fun VocaFullScreenPreview() {
    MemorizingVocaTheme {
        VocaFullScreen(
            title = "Day 01",
            blindMode = false,
            autoMode = false,
            page = 2,
            totalPage = 4,
            settledPage = 2,
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
                ),
                Vocabulary(
                    vocaId = 2,
                    day = 1,
                    word = "advocate",
                    meaning = "[동] ① 지지하다, 옹호하다 ② 주장하다 [명] 지지자, 옹호자",
                    highlighted = true,
                ),
            ),
            onBlindModeChange = {},
            onAutoModeChange = {},
            onNavigateBack = {},
            onPageChange = {},
            onSettledPageChange = {},
        )
    }
}
