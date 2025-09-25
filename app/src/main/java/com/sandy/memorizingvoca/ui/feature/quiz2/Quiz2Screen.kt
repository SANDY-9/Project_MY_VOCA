package com.sandy.memorizingvoca.ui.feature.quiz2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sandy.memorizingvoca.data.model.Vocabulary
import com.sandy.memorizingvoca.ui.common.QuizProgressIndicator
import com.sandy.memorizingvoca.ui.feature.quiz2.components.Quiz2QuizView
import com.sandy.memorizingvoca.ui.feature.quiz2.components.Quiz2TopBar
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme

@Composable
internal fun Quiz2Route(
    onNavigateBack: () -> Unit,
    onNavigateResult: (String) -> Unit,
    viewModel: Quiz2ViewModel = hiltViewModel(),
) {
    val quiz2State by viewModel.quiz2State.collectAsStateWithLifecycle()
    val gameSetState by viewModel.gameSetState.collectAsStateWithLifecycle()

    Quiz2Screen(
        quizStatus = quiz2State.guizStatus,
        title = quiz2State.title,
        remainsCount = quiz2State.remainsCount,
        totalCount = quiz2State.totalCount,
        onNavigateBack = onNavigateBack,
        cardList = gameSetState.gameSet,
        gameStatus = gameSetState.gameStatus,
        firstSelectedCard = gameSetState.firstSelectedCard,
        secondSelectedCard = gameSetState.secondSelectedCard,
        onCardSelect = viewModel::selectCard,
    )
}

@Composable
private fun Quiz2Screen(
    quizStatus: Quiz2Status,
    title: String,
    remainsCount: Int,
    totalCount: Int,
    cardList: List<VocaCardState>,
    gameStatus: GameSetStatus,
    firstSelectedCard: VocaCardState?,
    secondSelectedCard: VocaCardState?,
    onCardSelect: (VocaCardState) -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        Quiz2TopBar(
            title = title,
            totalCount = totalCount,
            remainsCount = remainsCount,
            onNavigateBack = onNavigateBack,
        )
        QuizProgressIndicator(
            progressed = quizStatus == Quiz2Status.STARTED,
            durationMillis = QUIZ2_TIME_OUT,
        )
        Quiz2QuizView(
            cardList = cardList,
            status = gameStatus,
            firstSelectedCard = firstSelectedCard,
            secondSelectedCard = secondSelectedCard,
            onCardSelect = onCardSelect,
        )
    }
}

@Preview
@Composable
private fun Quiz2ScreenPreview() {
    MemorizingVocaTheme {
        Quiz2Screen(
            quizStatus = Quiz2Status.STARTED,
            title = "Day 01",
            remainsCount = 19,
            totalCount = 50,
            cardList = listOf(
                VocaCardState(
                    type = VocaCardType.MEANING,
                    text = "advocate",
                    voca = Vocabulary(
                        vocaId = 2,
                        day = 1,
                        word = "advocate",
                        meaning = "[동] ① 지지하다, 옹호하다 ② 주장하다 [명] 지지자, 옹호자",
                    ),
                ),
                VocaCardState(
                    type = VocaCardType.MEANING,
                    text = "affluent",
                    voca = Vocabulary(
                        vocaId = 3,
                        day = 1,
                        word = "affluent",
                        meaning = "[형] ① 풍부한 ② 부유한"
                    ),
                ),
                VocaCardState(
                    type = VocaCardType.MEANING,
                    text = "ing 하는 데 어려움[곤란]을 겪다",
                    voca = Vocabulary(
                        vocaId = 5,
                        day = 1,
                        word = "have trouble[difficulty] (in)",
                        meaning = "ing 하는 데 어려움[곤란]을 겪다",
                    ),
                ),
                VocaCardState(
                    type = VocaCardType.MEANING,
                    text = "have trouble[difficulty] (in)",
                    voca = Vocabulary(
                        vocaId = 5,
                        day = 1,
                        word = "have trouble[difficulty] (in)",
                        meaning = "ing 하는 데 어려움[곤란]을 겪다",
                    ),
                ),
                VocaCardState(
                    type = VocaCardType.MEANING,
                    text = "[동] ① 지지하다, 옹호하다 ② 주장하다 [명] 지지자, 옹호자",
                    voca = Vocabulary(
                        vocaId = 2,
                        day = 1,
                        word = "advocate",
                        meaning = "[동] ① 지지하다, 옹호하다 ② 주장하다 [명] 지지자, 옹호자",
                    ),
                ),
                VocaCardState(
                    type = VocaCardType.MEANING,
                    text = "[형] ① 풍부한 ② 부유한",
                    voca = Vocabulary(
                        vocaId = 3,
                        day = 1,
                        word = "affluent",
                        meaning = "[형] ① 풍부한 ② 부유한",
                    ),
                ),
            ),
            gameStatus = GameSetStatus.NONE,
            firstSelectedCard = null,
            secondSelectedCard = null,
            onCardSelect = {},
            onNavigateBack = {},
        )
    }
}
