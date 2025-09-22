package com.sandy.memorizingvoca.ui.feature.voca_list.navigation

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.sandy.memorizingvoca.ui.feature.voca_list.VocaListRoute
import kotlinx.serialization.Serializable

@Serializable
data class VocaListRoute(val day: Int)

fun NavController.navigateToList(
    day: Int,
    navOptions: NavOptions? = null,
) {
    navigate(route = VocaListRoute(day), navOptions)
}

fun NavGraphBuilder.vocaListScreen(
    onVocaItemClick: (Int) -> Unit = {},
) {
    composable<VocaListRoute> {
        val args = it.toRoute<VocaListRoute>()
        VocaListRoute (
            day = args.day,
            onItemClick = onVocaItemClick,
        )
    }
}