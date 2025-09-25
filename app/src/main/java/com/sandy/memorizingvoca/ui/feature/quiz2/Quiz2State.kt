package com.sandy.memorizingvoca.ui.feature.quiz2

import com.sandy.memorizingvoca.data.model.Vocabulary

internal data class Quiz2State(
    val title: String = "",
    val vocaListSets: List<List<Vocabulary>> = emptyList(),
    val correctCount: Int = 0,
    val totalCount: Int = 0,
    val incorrectedList: List<Vocabulary> = emptyList(),
    val quizDate: String? = null,
    val guizStatus: Quiz2Status = Quiz2Status.READY,
)

internal data class Quiz2GameSetState(
    val index: Int? = null, //게임이 진행할 때 불변
    val notCompleteList: List<Vocabulary> = emptyList(),
    val maxCompleteCount: Int = 0, //게임이 진행할 때 불변
    val gameSet: List<VocaCardState> = emptyList(),
    val firstSelectedCard: VocaCardState? = null,
    val secondSelectedCard: VocaCardState? = null,
    val gameStatus: GameSetStatus = GameSetStatus.NONE,
)

internal enum class VocaCardType {
    WORD,
    MEANING,
}

internal data class VocaCardState(
    val type: VocaCardType,
    val text: String,
    val voca: Vocabulary,
    val index: Int = 0,
    val isAnswered: Boolean = false,
)

internal enum class GameSetStatus {
    NONE,
    CORRECTED,
    INCORRECTED,
}

internal enum class Quiz2Status {
    READY,
    STARTED,
    COMPLETED,
    FINISHED,
}

const val QUIZ2_TIME_OUT = 12000