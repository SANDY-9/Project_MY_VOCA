package com.sandy.memorizingvoca.ui.feature.voca_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.sandy.memorizingvoca.data.repository.GetVocabularyRepository
import com.sandy.memorizingvoca.ui.feature.voca_list.navigation.VocaListRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class VocaListViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getVocabularyRepository: GetVocabularyRepository,
): ViewModel() {

    val day = savedStateHandle.toRoute<VocaListRoute>().day
    val vocaList = getVocabularyRepository.getVocaList(day).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList(),
    )

}