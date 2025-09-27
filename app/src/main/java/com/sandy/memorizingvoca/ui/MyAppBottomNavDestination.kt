package com.sandy.memorizingvoca.ui

import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.sandy.memorizingvoca.ui.feature.bookmark.navigation.BookmarkRoute
import com.sandy.memorizingvoca.ui.feature.home.navigation.HomeRoute
import com.sandy.memorizingvoca.ui.resources.Apps
import com.sandy.memorizingvoca.ui.resources.Star
import com.sandy.memorizingvoca.ui.resources.StarOutline
import com.sandy.memorizingvoca.ui.theme.Gray70
import com.sandy.memorizingvoca.ui.theme.Pink100
import kotlin.reflect.KClass

internal enum class MyAppBottomNavDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val title: String,
    val route: KClass<*>,
    val selectedColor: Color = Pink100,
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
        unselectedIcon = Icons.Rounded.StarOutline,
        title = "북마크",
        route = BookmarkRoute::class,
    )
}