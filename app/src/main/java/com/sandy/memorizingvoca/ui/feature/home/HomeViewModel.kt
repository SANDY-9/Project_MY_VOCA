package com.sandy.memorizingvoca.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandy.memorizingvoca.data.repository.GetQuizRepository
import com.sandy.memorizingvoca.data.repository.GetVocabularyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    getVocabularyRepository: GetVocabularyRepository,
    getQuizRepository: GetQuizRepository,
): ViewModel() {

    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState = _homeUiState.asStateFlow()

    init {
        getQuizRepository.getAllDaysWithCount().map { quizDays ->
            getVocabularyRepository.getVocaDayList().associateWith { day ->
                val quizDay = quizDays.find { day == it.day }
                return@associateWith quizDay?.count ?: 0
            }.toSortedMap()
        }.onEach {
            _homeUiState.value = homeUiState.value.copy(
                days = it,
            )
        }.launchIn(viewModelScope)
    }

    fun onPlayerOnAndOffChange(on: Boolean) {
        _homeUiState.value = homeUiState.value.copy(
            playerVisible = on,
            playerVisibleClick = on,
        )
    }

    fun initVisibleClickState() {
        _homeUiState.value = homeUiState.value.copy(
            playerVisibleClick = null,
        )
    }

}