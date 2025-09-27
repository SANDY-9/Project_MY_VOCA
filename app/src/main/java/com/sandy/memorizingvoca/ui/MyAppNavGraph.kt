package com.sandy.memorizingvoca.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import com.sandy.memorizingvoca.ui.feature.bookmark.navigation.bookmarkScreen
import com.sandy.memorizingvoca.ui.feature.calendar.navigation.calendarScreen
import com.sandy.memorizingvoca.ui.feature.home.navigation.homeScreen
import com.sandy.memorizingvoca.ui.feature.home.navigation.navigateToHome
import com.sandy.memorizingvoca.ui.feature.quiz1.navigation.Quiz1Route
import com.sandy.memorizingvoca.ui.feature.quiz1.navigation.navigateToQuiz1
import com.sandy.memorizingvoca.ui.feature.quiz1.navigation.quiz1Screen
import com.sandy.memorizingvoca.ui.feature.quiz2.navigation.Quiz2Route
import com.sandy.memorizingvoca.ui.feature.quiz2.navigation.navigateToQuiz2
import com.sandy.memorizingvoca.ui.feature.quiz2.navigation.quiz2Screen
import com.sandy.memorizingvoca.ui.feature.quiz_result.navigation.navigateToQuizResult
import com.sandy.memorizingvoca.ui.feature.quiz_result.navigation.quizResultScreen
import com.sandy.memorizingvoca.ui.feature.splash.navigation.SplashRoute
import com.sandy.memorizingvoca.ui.feature.splash.navigation.splashScreen
import com.sandy.memorizingvoca.ui.feature.voca_details.navigation.navigateToDetails
import com.sandy.memorizingvoca.ui.feature.voca_details.navigation.vocaDetailsScreen
import com.sandy.memorizingvoca.ui.feature.voca_fullscreen.navigation.navigateToVocaFullScreen
import com.sandy.memorizingvoca.ui.feature.voca_fullscreen.navigation.vocaFullScreen
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
            onNavigateFullScreen = navController::navigateToVocaFullScreen,
            onNavigateQuiz1 = navController::navigateToQuiz1,
            onNavigateQuiz2 = navController::navigateToQuiz2,
            onVocaItemClick = navController::navigateToDetails,
        )

        vocaDetailsScreen (
            onNavigateBack = navController::navigateUp,
        )

        vocaFullScreen(
            onNavigateBack = navController::navigateUp,
        )

        val quiz1NavOptions = navOptionBuilder.setPopUpTo<Quiz1Route>(inclusive = true).build()
        quiz1Screen(
            onNavigateBack = navController::navigateUp,
            onNavigateResult = { date ->
                navController.navigateToQuizResult(date, quiz1NavOptions)
            }
        )

        val quiz2NavOptions = navOptionBuilder.setPopUpTo<Quiz2Route>(inclusive = true).build()
        quiz2Screen(
            onNavigateBack = navController::navigateUp,
            onNavigateResult = { date ->
                navController.navigateToQuizResult(date, quiz2NavOptions)
            }
        )

        quizResultScreen(
            onNavigateBack = navController::navigateUp,
            onNavigateVocaDetails = navController::navigateToDetails,
        )

        bookmarkScreen(
            onNavigateFullScreen = navController::navigateToVocaFullScreen,
            onNavigateQuiz1 = navController::navigateToQuiz1,
            onNavigateQuiz2 = navController::navigateToQuiz2,
            onNavigateDetails = navController::navigateToDetails,
        )

        calendarScreen(
            navigateQuizResult = { date ->
                navController.navigateToQuizResult(date, null)
            }
        )

    }
}