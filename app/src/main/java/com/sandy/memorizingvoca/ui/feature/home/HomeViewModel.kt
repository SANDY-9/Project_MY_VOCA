package com.sandy.memorizingvoca.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandy.memorizingvoca.data.repository.GetQuizRepository
import com.sandy.memorizingvoca.data.repository.GetVocabularyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    getVocabularyRepository: GetVocabularyRepository,
    getQuizRepository: GetQuizRepository,
): ViewModel() {

    val days = getQuizRepository.getAllDaysWithCount().map { quizDays ->
        getVocabularyRepository.getVocaDayList().associateWith { day ->
            val quizDay = quizDays.find { day == it.day }
            return@associateWith quizDay?.count ?: 0
        }.toSortedMap()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyMap(),
    )

}