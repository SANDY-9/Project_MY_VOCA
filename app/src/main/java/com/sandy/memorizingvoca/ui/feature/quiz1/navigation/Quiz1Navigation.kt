package com.sandy.memorizingvoca.ui.feature.quiz1.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sandy.memorizingvoca.ui.feature.quiz1.Quiz1Route
import kotlinx.serialization.Serializable

@Serializable
object Quiz1Route

fun NavController.navigateToQuiz1(
    navOptions: NavOptions? = null,
) {
    navigate(route = Quiz1Route, navOptions)
}

fun NavGraphBuilder.quiz1Screen(
    onNavigateBack: () -> Unit,
) {
    composable<Quiz1Route> {
        Quiz1Route(
            onNavigateBack = onNavigateBack,
        )
    }
}