package com.sandy.memorizingvoca.ui.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sandy.memorizingvoca.ui.feature.home.HomeRoute
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

fun NavController.navigateToHome(navOptions: NavOptions) = navigate(route = HomeRoute, navOptions)

fun NavGraphBuilder.homeScreen(
    onAppFinish: () -> Unit,
    onNavigateList: (Int) -> Unit,
) {
    composable<HomeRoute> {
        HomeRoute(
            onAppFinish = onAppFinish,
            onNavigateList = onNavigateList,
        )
    }
}