package com.sandy.memorizingvoca.ui.feature.voca_details

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.sandy.memorizingvoca.data.model.Vocabulary
import com.sandy.memorizingvoca.data.model.VocabularyDetails
import com.sandy.memorizingvoca.data.repository.BookmarkRepository
import com.sandy.memorizingvoca.data.repository.GetVocabularyRepository
import com.sandy.memorizingvoca.data.repository.HighlightRepository
import com.sandy.memorizingvoca.ui.feature.voca_details.navigation.VocaDetailsRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class VocaDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getVocabularyRepository: GetVocabularyRepository,
    private val highlightRepository: HighlightRepository,
    private val bookmarkRepository: BookmarkRepository,
): ViewModel() {

    private val vocaId = savedStateHandle.toRoute<VocaDetailsRoute>().vocaId
    val voca = getVocabularyRepository.getVocabulary(vocaId).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = null,
    )

    private val _details = MutableStateFlow<VocabularyDetails?>(null)
    val details = _details.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val item = voca.first { it != null }
                val details = getVocabularyRepository.getVocabularyDetails(item!!.word)
                _details.update { details }
            } catch (e: Exception) {
                Log.e("[NETWORK_ERROR]", "${e.message}")
            }
        }
    }

    fun updateHighlight(highlighted: Boolean) = voca.value?.let {
        viewModelScope.launch {
            when {
                highlighted -> addHighlightVoca(it)
                else -> deleteHighlightVoca(it)
            }
        }
    }

    private suspend fun addHighlightVoca(voca: Vocabulary) {
        highlightRepository.addHighlight(
            vocaId = vocaId,
            day = voca.day,
            word = voca.word,
            meaning = voca.meaning,
            bookmarked = voca.bookmarked,
        )
    }

    private suspend fun deleteHighlightVoca(voca: Vocabulary) {
        highlightRepository.deleteHighlight(
            vocaId = vocaId,
            day = voca.day,
            word = voca.word,
            meaning = voca.meaning,
            bookmarked = voca.bookmarked,
        )
    }

    fun updateBookmark(bookmarked: Boolean) = voca.value?.let {
        viewModelScope.launch {
            when {
                bookmarked -> addBookmarkVoca(it)
                else -> deleteBookmarkVoca(it)
            }
        }
    }

    private suspend fun addBookmarkVoca(voca: Vocabulary) {
        bookmarkRepository.addBookmark(
            vocaId = vocaId,
            day = voca.day,
            word = voca.word,
            meaning = voca.meaning,
            highlighted = voca.highlighted,
        )
    }

    private suspend fun deleteBookmarkVoca(voca: Vocabulary) {
        bookmarkRepository.deleteBookmark(
            vocaId = vocaId,
            day = voca.day,
            word = voca.word,
            meaning = voca.meaning,
            highlighted = voca.highlighted,
        )
    }

}

data class Grammar(
    val sort: String,
    val words: Map<String, String>
)