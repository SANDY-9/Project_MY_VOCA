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
        )
    }
}