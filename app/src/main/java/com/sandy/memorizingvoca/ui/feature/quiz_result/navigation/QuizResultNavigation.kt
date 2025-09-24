package com.sandy.memorizingvoca.ui.feature.quiz_result.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sandy.memorizingvoca.ui.feature.quiz1.Quiz1Route
import com.sandy.memorizingvoca.ui.feature.quiz_result.QuizResultRoute
import kotlinx.serialization.Serializable

@Serializable
data class QuizResultRoute(val date: String)

fun NavController.navigateToQuizResult(
    date: String,
    navOptions: NavOptions? = null,
) {
    navigate(route = QuizResultRoute(date), navOptions)
}

fun NavGraphBuilder.quizResultScreen(
    onNavigateBack: () -> Unit,
    onNavigateVocaDetails: (Int) -> Unit,
) {
    composable<QuizResultRoute> {
        QuizResultRoute(
            onNavigateBack = onNavigateBack,
            onNavigateVocaDetails = onNavigateVocaDetails,
        )
    }
}