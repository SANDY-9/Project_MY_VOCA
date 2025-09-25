package com.sandy.memorizingvoca.ui.feature.quiz2

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.sandy.memorizingvoca.data.model.Vocabulary
import com.sandy.memorizingvoca.data.repository.BookmarkRepository
import com.sandy.memorizingvoca.data.repository.GetVocabularyRepository
import com.sandy.memorizingvoca.data.repository.QuizRepository
import com.sandy.memorizingvoca.ui.feature.quiz2.navigation.Quiz2Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
internal class Quiz2ViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getVocabularyRepository: GetVocabularyRepository,
    private val getBookmarkRepository: BookmarkRepository,
    private val quizRepository: QuizRepository,
): ViewModel() {

    private val day = savedStateHandle.toRoute<Quiz2Route>().day

    private val _quiz2State = MutableStateFlow(Quiz2State())
    val quiz2State = _quiz2State.asStateFlow()

    private val _gameSetState = MutableStateFlow(Quiz2GameSetState())
    val gameSetState = _gameSetState.asStateFlow()

    init {
        viewModelScope.launch {
            quiz2State
                .map { it.guizStatus }
                .distinctUntilChanged()
                .collectLatest { status ->
                    when (status) {
                        Quiz2Status.READY -> {
                            initQuiz2UiState()
                            nextGameSet()
                        }

                        Quiz2Status.STARTED -> {
                            delay(QUIZ2_TIME_OUT.toLong())
                            updateCompletedGameSet()
                        }

                        Quiz2Status.COMPLETED -> {
                            delay(100L)
                            nextGameSet()
                        }

                        Quiz2Status.FINISHED -> {
                            requestQuizResult()
                        }
                    }
                }
        }
        gameSetState
            .filter {
                it.gameStatus != GameSetStatus.NONE
            }
            .distinctUntilChanged()
            .onEach {
                val prvGameSetState = gameSetState.value
                delay(200L)
                if(prvGameSetState.notCompleteList.size == gameSetState.value.notCompleteList.size) {
                    resetGameStatus(prvGameSetState)
                }
            }.launchIn(viewModelScope)
    }

    private suspend fun initQuiz2UiState() {
        val vocaList = downloadVocaList(day)
        val vocaListSet = vocaList.chunked(6)
        _quiz2State.value = Quiz2State(
            title = getQuiz1Title(day),
            vocaListSets = vocaListSet,
            correctCount = 0,
            totalCount = vocaList.size,
            totalPage = vocaListSet.size,
            incorrectedSet = emptySet(),
            quizDate = LocalDateTime.now().toString(),
            guizStatus = Quiz2Status.READY,
        )
    }

    private fun getQuiz1Title(day: Int): String {
        return when {
            day > 0 -> "Day " + String.format("%02d", day)
            else -> "Bookmark"
        }
    }

    private suspend fun downloadVocaList(day: Int): List<Vocabulary> {
        return when {
            day > 0 -> getVocabularyRepository.getVocaList(day).first()
            else -> getBookmarkRepository.getBookmarkList().first().flatMap { it.value }
        }.shuffled()
    }

    private fun nextGameSet() {
        val nextIndex = (gameSetState.value.index ?: -1) + 1
        val quizListSets = quiz2State.value.vocaListSets
        if(nextIndex == quizListSets.size) {
           updateQuizStatus(Quiz2Status.FINISHED)
           return
        }

        val notCompleteList = quizListSets[nextIndex]
        _gameSetState.value = Quiz2GameSetState(
            index = nextIndex,
            notCompleteList = notCompleteList,
            maxCompleteCount = notCompleteList.size,
            gameSet = createGameSet(notCompleteList),
            firstSelectedCard = null,
            secondSelectedCard = null,
            gameStatus = GameSetStatus.NONE,
        )
        updateQuizStatus(Quiz2Status.STARTED)
    }

    private fun createGameSet(vocaList: List<Vocabulary>) : List<VocaCardState> {
        val wordCardList = vocaList.map {
            VocaCardState(
                type = VocaCardType.WORD,
                text = it.word,
                voca = it,
                isAnswered = false,
            )
        }
        val meaningCardList = vocaList.map {
            VocaCardState(
                type = VocaCardType.MEANING,
                text = it.meaning,
                voca = it,
                isAnswered = false,
            )
        }
        return (wordCardList + meaningCardList).shuffled().mapIndexed { index, vocaCardState ->
            vocaCardState.copy(index = index)
        }
    }

    private fun updateQuizStatus(quiz2Status: Quiz2Status) {
        _quiz2State.value = quiz2State.value.copy(guizStatus = quiz2Status)
    }

    fun selectCard(newCard: VocaCardState) {
        val firstCard = gameSetState.value.firstSelectedCard
        val secondCard = gameSetState.value.secondSelectedCard
        if(newCard == firstCard || newCard == secondCard) return

        checkfirstCard(firstCard, newCard)
        checkAnswer(firstCard ?: return, newCard)
    }

    private fun checkfirstCard(firstCard: VocaCardState?, newCard: VocaCardState) {
        if(firstCard == null) {
            updateGameSetState(firstSelectedCard = newCard)
        }
    }

    private fun checkAnswer(
        firstCard: VocaCardState,
        newCard: VocaCardState,
    ) {
        val isAnswered = firstCard.voca == newCard.voca
        if(isAnswered) { // 정답 처리
            updateGameSetState(
                firstSelectedCard = firstCard,
                secondSelectedCard = newCard,
                gameStatus = GameSetStatus.CORRECTED,
            )
            updateCorrectCount()
        }
        else { // 오답 처리
            updateGameSetState(
                firstSelectedCard = firstCard,
                secondSelectedCard = newCard,
                gameStatus = GameSetStatus.INCORRECTED,
            )
        }
    }

    private fun updateGameSetState(
        notCompleteList: List<Vocabulary> = gameSetState.value.notCompleteList,
        gameSet: List<VocaCardState> = gameSetState.value.gameSet,
        firstSelectedCard: VocaCardState? = gameSetState.value.firstSelectedCard,
        secondSelectedCard: VocaCardState? = gameSetState.value.secondSelectedCard,
        gameStatus: GameSetStatus = gameSetState.value.gameStatus,
    ) {
        _gameSetState.value = gameSetState.value.copy(
            notCompleteList = notCompleteList,
            gameSet = gameSet,
            firstSelectedCard = firstSelectedCard,
            secondSelectedCard = secondSelectedCard,
            gameStatus = gameStatus,
        )
    }

    private fun updateCorrectCount() {
        _quiz2State.update { it.copy(correctCount = it.correctCount + 1) }
    }

    private fun resetGameStatus(curGameSetState: Quiz2GameSetState) {
        val firstCard = curGameSetState.firstSelectedCard ?: return
        val secondCard = curGameSetState.secondSelectedCard ?: return
        val firstGameStatus = curGameSetState.gameStatus
        val notCompleteList = getUpdatedCompleteList(firstGameStatus, firstCard.voca)
        updateGameSetState(
            notCompleteList = notCompleteList,
            gameSet = getUpdatedGameSet(firstGameStatus, firstCard, secondCard),
            firstSelectedCard = null,
            secondSelectedCard = null,
            gameStatus = GameSetStatus.NONE,
        )

        if(notCompleteList.isEmpty()) {
            updateCompletedGameSet()
        }
    }

    private fun getUpdatedGameSet(
        firstGameStatus: GameSetStatus,
        vararg cards: VocaCardState,
    ): List<VocaCardState> {
        return gameSetState.value.gameSet.toMutableList().also {
            if(firstGameStatus == GameSetStatus.CORRECTED) {
                cards.forEach { card ->
                    it[card.index] = card.copy(isAnswered = true)
                }
            }
        }
    }

    private fun getUpdatedCompleteList(
        firstGameStatus: GameSetStatus,
        voca: Vocabulary,
    ): List<Vocabulary> {
        return gameSetState.value.notCompleteList.toMutableList().apply {
            if(firstGameStatus == GameSetStatus.CORRECTED) {
                remove(voca)
            }
        }
    }

    private fun updateCompletedGameSet() {
        val incorrectedList = gameSetState.value.notCompleteList
        _quiz2State.value = quiz2State.value.copy(
            incorrectedSet = quiz2State.value.incorrectedSet + incorrectedList,
            guizStatus = Quiz2Status.COMPLETED,
        )
    }

    private fun requestQuizResult() = CoroutineScope(Dispatchers.IO).launch {
        val quiz2State = quiz2State.value
        quizRepository.addNewQuizResult(
            date = quiz2State.quizDate ?: return@launch,
            day = day,
            totalCount = quiz2State.totalCount,
            incorrectedVocaId = quiz2State.incorrectedSet.map { it.vocaId },
        )
    }

}