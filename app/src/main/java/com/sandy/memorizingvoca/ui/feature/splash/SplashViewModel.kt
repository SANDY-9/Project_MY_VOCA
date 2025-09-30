package com.sandy.memorizingvoca.ui.feature.splash

import android.content.res.AssetManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandy.memorizingvoca.data.model.Vocabulary
import com.sandy.memorizingvoca.data.repository.VocabularyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
internal class SplashViewModel @Inject constructor(
    private val vocabularyRepository: VocabularyRepository,
): ViewModel() {

    private val _splashUiState: MutableStateFlow<SplashUiState> = MutableStateFlow(SplashUiState.Loading)
    val splashUiState = _splashUiState.asStateFlow()

    fun downloadVocabulary(assetManager: AssetManager) {
        flow {
            val data = assetManager
                .open(VOCABULARY_DATA_FILE_NAME)
                .bufferedReader()
                .readLines().map {
                    val token = it.split("\t")
                    Vocabulary(
                        vocaId = token[0].toInt(),
                        day = token[1].toInt(),
                        word = token[2].trim(),
                        meaning = token[3].trim(),
                        pron = token[4].trim().takeIf { pron -> pron != "null" },
                    )
                }
            emit(vocabularyRepository.addVocabularyList(data))
        }.onEach {
            _splashUiState.value = SplashUiState.Complete
        }.catch {
            _splashUiState.value = SplashUiState.Fail
        }.launchIn(viewModelScope)
    }
}

private const val VOCABULARY_DATA_FILE_NAME = "voca.txt"