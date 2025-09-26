package com.sandy.memorizingvoca.ui.feature.bookmark.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sandy.memorizingvoca.ui.feature.bookmark.BookmarkRoute
import kotlinx.serialization.Serializable

@Serializable
object BookmarkRoute

fun NavController.navigateToBookmark(
    navOptions: NavOptions? = null,
) {
    navigate(route = BookmarkRoute, navOptions)
}

private const val BOOKMARK_DAY_NUMBER = 0
fun NavGraphBuilder.bookmarkScreen(
    onNavigateFullScreen: (Int) -> Unit,
    onNavigateQuiz1: (Int) -> Unit,
    onNavigateQuiz2: (Int) -> Unit,
) {
    composable<BookmarkRoute> {
        BookmarkRoute(
            onNavigateFullScreen = {
                onNavigateFullScreen(BOOKMARK_DAY_NUMBER)
            },
            onNavigateQuiz1 = {
                onNavigateQuiz1(BOOKMARK_DAY_NUMBER)
            },
            onNavigateQuiz2 = {
                onNavigateQuiz2(BOOKMARK_DAY_NUMBER)
            },
        )
    }
}