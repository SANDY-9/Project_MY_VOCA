package com.sandy.memorizingvoca.ui.feature.voca_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.sandy.memorizingvoca.data.repository.BookmarkRepository
import com.sandy.memorizingvoca.data.repository.GetVocabularyRepository
import com.sandy.memorizingvoca.data.repository.HighlightRepository
import com.sandy.memorizingvoca.ui.feature.voca_details.navigation.VocaDetailsRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class VocaDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getVocabularyRepository: GetVocabularyRepository,
    private val bookmarkRepository: BookmarkRepository,
    private val highlightRepository: HighlightRepository,
): ViewModel() {

    private val vocaId = savedStateHandle.toRoute<VocaDetailsRoute>().vocaId
    val voca = getVocabularyRepository.getVocabularyDetails(vocaId).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = null,
    )

}