package com.sandy.memorizingvoca.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import com.sandy.memorizingvoca.ui.feature.home.navigation.homeScreen
import com.sandy.memorizingvoca.ui.feature.home.navigation.navigateToHome
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
            onDayItemClick = { day ->
                navController.navigateToList(day)
            }
        )

        vocaListScreen(
            onNavigateBack = navController::navigateUp,
            onNavigateFull = {},
            onNavigateQuiz1 = {},
            onNavigateQuiz2 = {},
            onVocaItemClick = { vocaId ->
                navController.navigateToDetails(vocaId)
            }
        )

        vocaDetailsScreen (
            onNavigateBack = navController::navigateUp,
        )
    }
}