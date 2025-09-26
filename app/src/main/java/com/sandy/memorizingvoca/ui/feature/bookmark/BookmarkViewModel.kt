package com.sandy.memorizingvoca.ui.feature.bookmark

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandy.memorizingvoca.data.model.Vocabulary
import com.sandy.memorizingvoca.data.repository.BookmarkRepository
import com.sandy.memorizingvoca.data.repository.GetVocabularyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class BookmarkViewModel @Inject constructor(
    getVocabularyRepository: GetVocabularyRepository,
    private val bookmarkRepository: BookmarkRepository,
): ViewModel() {

    private val _bookmarkUiState = MutableStateFlow(BookmarkUiState())
    val bookmarkUiState = _bookmarkUiState.asStateFlow()

    init {
        getVocabularyRepository.getBookmarkList().onEach { bookmarkList ->
            val items = getFilteredBookmarkMap(
                query = bookmarkUiState.value.query,
                originalList = bookmarkList,
            )
            _bookmarkUiState.value = BookmarkUiState(
                bookmarkList = bookmarkList,
                bookmarkCount = bookmarkList.size,
                filteredBookmarkMapByDay = items,
                itemCount = items.itemCount(),
            )
        }.catch { e ->
            Log.e("[BOOKMARK_ERROR]", "${e.message}")
        }.launchIn(viewModelScope)
    }

    private fun getFilteredBookmarkMap(
        query: String?,
        originalList: List<Vocabulary> = bookmarkUiState.value.bookmarkList,
    ): Map<Int, List<Vocabulary>> {
        if(query == null) return originalList.bookmarkMap()
        try {
            // query가 숫자일 경우
            val day = query.toInt()
            val filteredList = originalList.filter { it.day == day }
            return filteredList.bookmarkMap()
        } catch (e: Exception) {
            // query가 숫자가 아닐 경우
            val filteredList = originalList.filter {
                it.word.contains(query) || it.meaning.contains(query)
            }
            return filteredList.bookmarkMap()
        }
    }

    private fun List<Vocabulary>.bookmarkMap(): Map<Int, List<Vocabulary>> {
        return groupBy { it.day }.toSortedMap()
    }

    private fun Map<Int, List<Vocabulary>>.itemCount(): Int {
        return values.sumOf { it.size }
    }

    fun onBlindModeChange(isBlindMode: Boolean) {
        _bookmarkUiState.update { it.copy(blindMode = isBlindMode) }
    }

    fun searchVoca(query: String) {
        val newQuery = query.trim()
        val result = getFilteredBookmarkMap(newQuery)
        _bookmarkUiState.update {
            it.copy(
                filteredBookmarkMapByDay = result,
                query = newQuery,
                itemCount = result.itemCount(),
            )
        }
    }

    fun resetBookmarkList() {
        _bookmarkUiState.update {
            it.copy(
                filteredBookmarkMapByDay = getFilteredBookmarkMap(null),
                query = null,
                itemCount = bookmarkUiState.value.bookmarkCount,
            )
        }
    }

    fun deleteBookmark(voca: Vocabulary) = viewModelScope.launch {
        bookmarkRepository.deleteBookmark(
            vocaId = voca.vocaId,
            day = voca.day,
            word = voca.word,
            meaning = voca.meaning,
            highlighted = voca.highlighted,
        )
    }

    fun deleteMultipleBookmark() = viewModelScope.launch {
        val updateList = bookmarkUiState.value.filteredBookmarkMapByDay.flatMap { it.value }
        bookmarkRepository.deleteMutipleBookmark(
            vocaList = updateList,
        )
    }

}