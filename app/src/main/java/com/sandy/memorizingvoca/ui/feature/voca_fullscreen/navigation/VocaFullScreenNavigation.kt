package com.sandy.memorizingvoca.ui.feature.voca_fullscreen.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sandy.memorizingvoca.ui.feature.voca_fullscreen.VocaFullScreenRoute
import kotlinx.serialization.Serializable

@Serializable
data class VocaFullScreenRoute(val day: Int)

fun NavController.navigateToVocaFullScreen(
    day: Int,
    navOptions: NavOptions? = null,
) {
    navigate(route = VocaFullScreenRoute(day), navOptions)
}

fun NavGraphBuilder.vocaFullScreen(
    onNavigateBack: () -> Unit,
) {
    composable<VocaFullScreenRoute> {
        VocaFullScreenRoute (
            onNavigateBack = onNavigateBack,
        )
    }
}