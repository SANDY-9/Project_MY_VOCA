package com.sandy.memorizingvoca.ui.feature.voca_fullscreen

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.sandy.memorizingvoca.data.model.Vocabulary
import com.sandy.memorizingvoca.data.repository.BookmarkRepository
import com.sandy.memorizingvoca.data.repository.GetVocabularyRepository
import com.sandy.memorizingvoca.ui.feature.voca_fullscreen.navigation.VocaFullScreenRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class VocaFullScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getVocabularyRepository: GetVocabularyRepository,
    private val getBookmarkRepository: BookmarkRepository,
): ViewModel() {

    private val day = savedStateHandle.toRoute<VocaFullScreenRoute>().day

    private val _fullScreenState = MutableStateFlow(VocaFullScreenState())
    val fullScreenState = _fullScreenState.asStateFlow()

    init {
        downloadVocaListFlow(day).onEach { vocaList ->
            _fullScreenState.value = VocaFullScreenState(
                title = getDayTitle(day),
                vocaList = vocaList,
                settledPage = 1,
                autoMode = false,
                blindMode = false,
                currentPage = 1,
                totalPage = vocaList.size,
            )
        }.catch {
            Log.e("[DATABASE_ERROR]", "${it.message}")
        }.launchIn(viewModelScope)
    }

    private  fun downloadVocaListFlow(day: Int): Flow<List<Vocabulary>> {
        return when {
            day > 0 -> getVocabularyRepository.getVocaList(day)
            else -> getBookmarkRepository.getBookmarkList()
        }
    }

    private fun getDayTitle(day: Int): String {
        return when {
            day > 0 -> "Day " + String.format("%02d", day)
            else -> "북마크"
        }
    }

    fun onBlindModeChange(isBlindMode: Boolean) {
        _fullScreenState.update { it.copy(blindMode = isBlindMode) }
    }

    fun onAutoModeChange(isAutoMode: Boolean) {
        _fullScreenState.update { it.copy(autoMode = isAutoMode) }
    }

    fun onPageChange(index: Int) {
        _fullScreenState.update { it.copy(currentPage = index + 1,) }
    }

    fun onSettledPageChange(index: Int) {
        _fullScreenState.update { it.copy(settledPage = index + 1,) }
    }

}