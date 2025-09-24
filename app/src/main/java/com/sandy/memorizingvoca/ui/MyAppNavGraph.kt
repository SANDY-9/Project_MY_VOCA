package com.sandy.memorizingvoca.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import com.sandy.memorizingvoca.ui.feature.home.navigation.homeScreen
import com.sandy.memorizingvoca.ui.feature.home.navigation.navigateToHome
import com.sandy.memorizingvoca.ui.feature.quiz1.navigation.Quiz1Route
import com.sandy.memorizingvoca.ui.feature.quiz1.navigation.navigateToQuiz1
import com.sandy.memorizingvoca.ui.feature.quiz1.navigation.quiz1Screen
import com.sandy.memorizingvoca.ui.feature.quiz_result.navigation.navigateToQuizResult
import com.sandy.memorizingvoca.ui.feature.quiz_result.navigation.quizResultScreen
import com.sandy.memorizingvoca.ui.feature.splash.navigation.SplashRoute
import com.sandy.memorizingvoca.ui.feature.splash.navigation.splashScreen
import com.sandy.memorizingvoca.ui.feature.voca_details.navigation.navigateToDetails
import com.sandy.memorizingvoca.ui.feature.voca_details.navigation.vocaDetailsScreen
import com.sandy.memorizingvoca.ui.feature.voca_list.navigation.navigateToList
import com.sandy.memorizingvoca.ui.feature.voca_list.navigation.vocaListScreen

@Composable
internal fun MyAppNavGraph(
    navController: NavHostController,
    startDestination: Any,
    onAppFinish: () -> Unit,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        val navOptionBuilder = NavOptions.Builder()
        val splashNavOptions = navOptionBuilder.setPopUpTo<SplashRoute>(inclusive = true).build()
        splashScreen(
            onAppFinish = onAppFinish,
            onComplete = {
                navController.navigateToHome(navOptions = splashNavOptions)
            },
        )

        homeScreen(
            onAppFinish = onAppFinish,
            onDayItemClick = navController::navigateToList
        )

        vocaListScreen(
            onNavigateBack = navController::navigateUp,
            onNavigateFull = {},
            onNavigateQuiz1 = navController::navigateToQuiz1,
            onNavigateQuiz2 = {},
            onVocaItemClick = navController::navigateToDetails
        )

        vocaDetailsScreen (
            onNavigateBack = navController::navigateUp,
        )

        val quiz1NavOptions = navOptionBuilder.setPopUpTo<Quiz1Route>(inclusive = true).build()
        quiz1Screen(
            onNavigateBack = navController::navigateUp,
            onNavigateResult = { date ->
                navController.navigateToQuizResult(date, quiz1NavOptions)
            }
        )

        quizResultScreen(
            onNavigateBack = navController::navigateUp,
        )

    }
}