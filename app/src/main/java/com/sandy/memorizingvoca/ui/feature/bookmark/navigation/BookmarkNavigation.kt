package com.sandy.memorizingvoca.ui.feature.bookmark.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sandy.memorizingvoca.ui.feature.bookmark.BookmarkRoute
import com.sandy.memorizingvoca.ui.feature.home.HomeRoute
import com.sandy.memorizingvoca.ui.feature.home.navigation.HomeRoute
import kotlinx.serialization.Serializable

@Serializable
object BookmarkRoute

fun NavController.navigateToBookmark(
    navOptions: NavOptions? = null,
) {
    navigate(route = BookmarkRoute, navOptions)
}

fun NavGraphBuilder.bookmarkScreen(
) {
    composable<BookmarkRoute> {
        BookmarkRoute(
        )
    }
}