package com.sandy.memorizingvoca.ui.feature.quiz1.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sandy.memorizingvoca.ui.feature.quiz1.Quiz1Route
import kotlinx.serialization.Serializable

@Serializable
data class Quiz1Route(val day: Int)

fun NavController.navigateToQuiz1(
    day: Int,
    navOptions: NavOptions? = null,
) {
    navigate(route = Quiz1Route(day), navOptions)
}

fun NavGraphBuilder.quiz1Screen(
    onNavigateBack: () -> Unit,
    onNavigateResult: (String) -> Unit,
) {
    composable<Quiz1Route> {
        Quiz1Route(
            onNavigateBack = onNavigateBack,
            onNavigateResult = onNavigateResult,
        )
    }
}