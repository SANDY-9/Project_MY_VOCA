package com.sandy.memorizingvoca.ui.feature.quiz2.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sandy.memorizingvoca.ui.feature.quiz1.Quiz1Route
import com.sandy.memorizingvoca.ui.feature.quiz2.Quiz2Route
import kotlinx.serialization.Serializable

@Serializable
data class Quiz2Route(val day: Int)

fun NavController.navigateToQuiz2(
    day: Int,
    navOptions: NavOptions? = null,
) {
    navigate(route = Quiz2Route(day), navOptions)
}

fun NavGraphBuilder.quiz2Screen(
    onNavigateBack: () -> Unit,
    onNavigateResult: (String) -> Unit,
) {
    composable<Quiz2Route> {
        Quiz2Route (
            onNavigateBack = onNavigateBack,
            onNavigateResult = onNavigateResult,
        )
    }
}