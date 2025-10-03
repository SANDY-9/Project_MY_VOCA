package com.sandy.memorizingvoca.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.sandy.memorizingvoca.ui.feature.bookmark.navigation.BookmarkRoute
import com.sandy.memorizingvoca.ui.feature.calendar.navigation.CalendarRoute
import com.sandy.memorizingvoca.ui.feature.home.navigation.HomeRoute
import com.sandy.memorizingvoca.ui.resources.Apps
import com.sandy.memorizingvoca.ui.resources.Calendar
import com.sandy.memorizingvoca.ui.resources.Star
import com.sandy.memorizingvoca.ui.theme.Gray70
import kotlin.reflect.KClass

internal enum class MyAppBottomNavDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val title: String,
    val route: KClass<*>,
    val unselectedColor: Color = Gray70,
) {
    HOME(
        selectedIcon = Icons.Rounded.Apps,
        unselectedIcon = Icons.Rounded.Apps,
        title = "홈",
        route = HomeRoute::class,
    ),
    BOOKMARK(
        selectedIcon = Icons.Rounded.Star,
        unselectedIcon = Icons.Rounded.Star,
        title = "북마크",
        route = BookmarkRoute::class,
    ),
    CALENDAR(
        selectedIcon = Icons.Rounded.Calendar,
        unselectedIcon = Icons.Rounded.Calendar,
        title = "캘린더",
        route = CalendarRoute::class,
    )
}