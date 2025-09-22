package com.sandy.memorizingvoca.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
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
        splashScreen(
            onAppFinish = onAppFinish,
            onComplete = {},
        )
    }
}