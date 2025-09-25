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
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
internal class Quiz2ViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getVocabularyRepository: GetVocabularyRepository,
    private val getBookmarkRepository: BookmarkRepository,
): ViewModel() {

    private val day = savedStateHandle.toRoute<Quiz2Route>().day

    private val _quiz2State = MutableStateFlow(Quiz2State())
    val quiz2State = _quiz2State.asStateFlow()

    private val _gameSetState = MutableStateFlow(Quiz2GameSetState())
    val gameSetState = _gameSetState.asStateFlow()

    init {
        viewModelScope.launch {
            quiz2State.map { it.guizStatus }.collectLatest { status ->
                when(status) {
                    Quiz2Status.READY -> {
                        initQuiz2UiState()
                        nextGameSet()
                    }
                    Quiz2Status.STARTED -> {
                        delay(QUIZ2_TIME_OUT.toLong())
                        updateCompletedGameSet()
                    }
                    Quiz2Status.COMPLETED -> {
                        nextGameSet()
                    }
                    Quiz2Status.FINISHED -> {

                    }
                }
            }
            gameSetState.filter {
                it.gameStatus !=  GameSetStatus.NONE
            }.collectLatest {
                delay(200L)
                resetGameStatus()
            }
        }
    }

    private suspend fun initQuiz2UiState() {
        val vocaList = downloadVocaList(day).chunked(10)
        _quiz2State.value = Quiz2State(
            title = getQuiz1Title(day),
            vocaListSets = vocaList,
            remainsCount = vocaList.size,
            totalCount = vocaList.size,
            incorrectedList = emptyList(),
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
            prevSelectedCard = null,
            curSelectedCard = null,
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
        val prevCard = gameSetState.value.prevSelectedCard
        val curCard = gameSetState.value.curSelectedCard
        if(newCard == prevCard || newCard == curCard) return

        checkPrevCard(prevCard, newCard)
        checkAnswer(prevCard ?: return, newCard)
    }

    private fun checkPrevCard(prevCard: VocaCardState?, newCard: VocaCardState) {
        if(prevCard == null) {
            updateGameSetState(prevSelectedCard = newCard)
        }
    }

    private fun checkAnswer(
        prevCard: VocaCardState,
        newCard: VocaCardState,
    ) {
        val isAnswered = prevCard.voca == newCard.voca
        if(isAnswered) { // 정답 처리
            updateGameSetState(
                prevSelectedCard = prevCard,
                curSelectedCard = newCard,
                gameStatus = GameSetStatus.CORRECTED,
            )
            updateRemainsCount()
        }
        else { // 오답 처리
            updateGameSetState(
                prevSelectedCard = prevCard,
                curSelectedCard = newCard,
                gameStatus = GameSetStatus.INCORRECTED,
            )
        }
    }

    private fun updateGameSetState(
        notCompleteList: List<Vocabulary> = gameSetState.value.notCompleteList,
        gameSet: List<VocaCardState> = gameSetState.value.gameSet,
        prevSelectedCard: VocaCardState? = gameSetState.value.prevSelectedCard,
        curSelectedCard: VocaCardState? = gameSetState.value.curSelectedCard,
        gameStatus: GameSetStatus = gameSetState.value.gameStatus,
    ) {
        _gameSetState.value = gameSetState.value.copy(
            notCompleteList = notCompleteList,
            gameSet = gameSet,
            prevSelectedCard = prevSelectedCard,
            curSelectedCard = curSelectedCard,
            gameStatus = gameStatus,
        )
    }

    private fun updateRemainsCount() {
        _quiz2State.update { it.copy(remainsCount = it.remainsCount - 1) }
    }

    private fun resetGameStatus() {
        val prevCard = gameSetState.value.prevSelectedCard ?: return
        val curCard = gameSetState.value.curSelectedCard ?: return
        val prevGameStatus = gameSetState.value.gameStatus
        val notCompleteList = getUpdatedCompleteList(prevGameStatus, prevCard.voca)
        updateGameSetState(
            notCompleteList = notCompleteList,
            gameSet = getUpdatedGameSet(prevGameStatus, prevCard, curCard),
            prevSelectedCard = null,
            curSelectedCard = null,
            gameStatus = GameSetStatus.NONE,
        )

        if(notCompleteList.isEmpty()) {
            updateCompletedGameSet()
        }
    }

    private fun getUpdatedGameSet(
        prevGameStatus: GameSetStatus,
        vararg cards: VocaCardState,
    ): List<VocaCardState> {
        return gameSetState.value.gameSet.toMutableList().also {
            if(prevGameStatus == GameSetStatus.CORRECTED) {
                cards.forEach { card ->
                    it[card.index] = card.copy(isAnswered = true)
                }
            }
        }
    }

    private fun getUpdatedCompleteList(
        prevGameStatus: GameSetStatus,
        voca: Vocabulary,
    ): List<Vocabulary> {
        return gameSetState.value.notCompleteList.toMutableList().apply {
            if(prevGameStatus == GameSetStatus.CORRECTED) {
                remove(voca)
            }
        }
    }

    private fun updateCompletedGameSet() {
        // TO_DO 오답 저장 로직 구혐
        val incorrectedList = gameSetState.value.notCompleteList

        updateQuizStatus(Quiz2Status.COMPLETED)
    }

}