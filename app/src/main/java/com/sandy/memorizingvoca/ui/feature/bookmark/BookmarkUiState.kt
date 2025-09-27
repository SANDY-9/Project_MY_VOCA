package com.sandy.memorizingvoca.ui.feature.bookmark

import com.sandy.memorizingvoca.data.model.Vocabulary

internal data class BookmarkUiState(
    val bookmarkList: List<Vocabulary> = emptyList(),
    val filteredBookmarkMapByDay: Map<Int, List<Vocabulary>> = emptyMap(),
    val bookmarkCount: Int = 0,
    val itemCount: Int = 0,
    val blindMode: Boolean = false,
    val query: String? = null,
    val currentQueryTitle: String = "전체",
)
