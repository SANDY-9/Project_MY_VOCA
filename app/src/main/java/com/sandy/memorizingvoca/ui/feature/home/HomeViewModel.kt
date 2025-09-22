package com.sandy.memorizingvoca.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandy.memorizingvoca.data.repository.GetVocabularyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val getVocabularyRepository: GetVocabularyRepository,
): ViewModel() {

    private val _days = MutableStateFlow(emptyList<Int>())
    val days = _days.asStateFlow()

    init {
        viewModelScope.launch {
            _days.value = getVocabularyRepository.getVocaDayList()
        }
    }
}