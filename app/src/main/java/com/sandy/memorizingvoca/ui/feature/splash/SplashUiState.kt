package com.sandy.memorizingvoca.ui.feature.splash

internal interface SplashUiState {
    data object Loading : SplashUiState
    data object Complete : SplashUiState
    data object Fail : SplashUiState
}