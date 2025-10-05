package com.sandy.memorizingvoca.ui.feature.calendar.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sandy.memorizingvoca.ui.feature.calendar.CalendarRoute
import kotlinx.serialization.Serializable

@Serializable
object CalendarRoute

fun NavController.navigateToCalendar(
    navOptions: NavOptions? = null
) {
    navigate(route = CalendarRoute, navOptions)
}

fun NavGraphBuilder.calendarScreen(
    onNavigateQuizResult: (String) -> Unit,
    onNavigateBack: () -> Unit,
) {
    composable<CalendarRoute> {
        CalendarRoute(
            onNavigateQuizResult = onNavigateQuizResult,
            onNavigateBack = onNavigateBack,
        )
    }
}