package com.sandy.memorizingvoca.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.sandy.memorizingvoca.ui.feature.bookmark.navigation.BookmarkRoute
import com.sandy.memorizingvoca.ui.feature.bookmark.navigation.navigateToBookmark
import com.sandy.memorizingvoca.ui.feature.home.navigation.HomeRoute
import com.sandy.memorizingvoca.ui.feature.home.navigation.navigateToHome

@Stable
internal class MyAppState(
    val navController: NavHostController,
) {
    private val previousDestination = mutableStateOf<NavDestination?>(null)

    val currentDestination: NavDestination?
        @Composable get() {
            // Collect the currentBackStackEntryFlow as a state
            val currentEntry = navController.currentBackStackEntryFlow
                .collectAsState(initial = null)

            // Fallback to previousDestination if currentEntry is null
            return currentEntry.value?.destination.also { destination ->
                if (destination != null) {
                    previousDestination.value = destination
                }
            } ?: previousDestination.value
        }

    fun navigateToDestination(destination: MyAppBottomNavDestination) {
        val topLevelNavOptions = navOptions {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
        when(destination) {
            MyAppBottomNavDestination.HOME -> navController.navigateToHome(topLevelNavOptions)
            MyAppBottomNavDestination.BOOKMARK -> navController.navigateToBookmark(topLevelNavOptions)
        }
    }

    @Composable
    fun isBottomNavVisible(): Boolean {
        val route = currentDestination?.route ?: ""
        return route in listOf(
            HomeRoute.javaClass.name,
            BookmarkRoute.javaClass.name,
        )
    }
}

@Composable
internal fun rememberMyAppState(
    navController: NavHostController = rememberNavController(),
): MyAppState {
    return remember(
        navController,
    ) {
        MyAppState(
            navController,
        )
    }
}