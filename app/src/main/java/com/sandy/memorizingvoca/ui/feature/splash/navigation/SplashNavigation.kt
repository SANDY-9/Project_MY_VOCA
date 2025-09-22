package com.sandy.memorizingvoca.ui.feature.splash.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sandy.memorizingvoca.ui.feature.splash.SplashRoute
import kotlinx.serialization.Serializable

@Serializable
object SplashRoute

fun NavController.navigateToSplash(navOptions: NavOptions) = navigate(route = SplashRoute, navOptions)

fun NavGraphBuilder.splashScreen(
    onComplete: () -> Unit,
    onAppFinish: () -> Unit,
) {
    composable<SplashRoute> {
        SplashRoute(
            onDownloadComplete = onComplete,
            onAppFinish = onAppFinish,
        )
    }
}