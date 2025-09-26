package com.sandy.memorizingvoca.ui.feature.voca_fullscreen

import com.sandy.memorizingvoca.data.model.Vocabulary

internal data class VocaFullScreenState(
    val title: String = "",
    val vocaList: List<Vocabulary> = emptyList(),
    val autoMode: Boolean = false,
    val blindMode: Boolean = false,
    val currentPage: Int = 0,
    val settledPage: Int = 0,
    val totalPage: Int = 0,
)
