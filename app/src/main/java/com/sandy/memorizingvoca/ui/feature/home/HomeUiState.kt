package com.sandy.memorizingvoca.ui.feature.home

internal data class HomeUiState(
    val days: Map<Int, Int> = emptyMap(),
    val playerVisible: Boolean = false,
    val playerVisibleClick: Boolean? = null,
)