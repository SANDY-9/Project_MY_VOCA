package com.sandy.memorizingvoca.ui.feature.voca_list.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sandy.memorizingvoca.ui.feature.voca_list.VocaListRoute
import kotlinx.serialization.Serializable

@Serializable
data class VocaListRoute(val day: Int)

fun NavController.navigateToList(
    day: Int,
    navOptions: NavOptions? = null,
) {
    navigate(route = VocaListRoute(day), navOptions)
}

fun NavGraphBuilder.vocaListScreen(
    onNavigateBack: () -> Unit,
    onNavigateFullScreen: (Int) -> Unit,
    onNavigateQuiz1: (Int) -> Unit,
    onNavigateQuiz2: (Int) -> Unit,
    onVocaItemClick: (Int) -> Unit,
) {
    composable<VocaListRoute> {
        VocaListRoute (
            onNavigateBack = onNavigateBack,
            onNavigateFullScreen = onNavigateFullScreen,
            onNavigateQuiz1 = onNavigateQuiz1,
            onNavigateQuiz2 = onNavigateQuiz2,
            onItemClick = onVocaItemClick,
        )
    }
}