package com.sandy.memorizingvoca.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.sandy.memorizingvoca.ui.feature.bookmark.navigation.BookmarkRoute
import com.sandy.memorizingvoca.ui.feature.bookmark.navigation.navigateToBookmark
import com.sandy.memorizingvoca.ui.feature.calendar.navigation.CalendarRoute
import com.sandy.memorizingvoca.ui.feature.calendar.navigation.navigateToCalendar
import com.sandy.memorizingvoca.ui.feature.home.navigation.HomeRoute
import com.sandy.memorizingvoca.ui.feature.home.navigation.navigateToHome
import java.util.Stack

@Stable
internal class MyAppState(
    val navController: NavHostController,
) {

    private val previousDestination = mutableStateOf<NavDestination?>(null)

    private val topLevelHistory = Stack<Int>()

    val currentDestination: NavDestination?
        @Composable get() {
            val currentEntry = navController.currentBackStackEntryFlow
                .collectAsState(initial = null)
            return currentEntry.value?.destination.also { destination ->
                if (destination != null) {
                    previousDestination.value = destination
                }
            } ?: previousDestination.value
        }

    fun navigateToDestination(destination: MyAppBottomNavDestination) {
        when(destination) {
            MyAppBottomNavDestination.HOME -> navigateToStartDestination(
                onNavigate = navController::navigateToHome
            )
            MyAppBottomNavDestination.BOOKMARK -> navigateToNotStartDestination(
                onNavigate = navController::navigateToBookmark
            )
            MyAppBottomNavDestination.CALENDAR -> navigateToNotStartDestination(
                onNavigate = navController::navigateToCalendar
            )
        }
    }

    private fun navigateToStartDestination(
        onNavigate: (NavOptions) -> Unit
    ) {
        val navOptions = navOptions {
            popUpTo(HomeRoute)
            launchSingleTop = true
            restoreState = true
        }
        onNavigate(navOptions)
        topLevelHistory.clear()
    }

    private fun navigateToNotStartDestination(
        onNavigate: (NavOptions) -> Unit
    ) {
        val navOptions = navOptions {
            launchSingleTop = true
            restoreState = true
        }
        onNavigate(navOptions)
        updateTopLevelHistory()
    }

    private fun updateTopLevelHistory() {
        val prevDestinationId = navController.previousBackStackEntry?.destination?.id ?: return
        topLevelHistory.remove(prevDestinationId)
        topLevelHistory.push(prevDestinationId)
    }


    fun navigateBackTopLevel() {
        val nextHistoryId = topLevelHistory.peek()
        topLevelHistory.pop()
        topLevelHistory.remove(navController.currentDestination?.id) //중복루트(자기자신) 제거
        navController.popBackStack(nextHistoryId, inclusive = false)
    }

    @Composable
    fun isBottomNavVisible(): Boolean {
        val route = currentDestination?.route ?: ""
        return route in listOf(
            HomeRoute.javaClass.name,
            BookmarkRoute.javaClass.name,
            CalendarRoute.javaClass.name,
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