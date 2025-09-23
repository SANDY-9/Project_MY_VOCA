package com.sandy.memorizingvoca.ui.feature.voca_details.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sandy.memorizingvoca.ui.feature.voca_details.VocaDetailsRoute
import kotlinx.serialization.Serializable

@Serializable
data class VocaDetailsRoute(val vocaId: Int,)

fun NavController.navigateToDetails(
    vocaId: Int,
    navOptions: NavOptions? = null,
) {
    navigate(route = VocaDetailsRoute(vocaId), navOptions)
}

fun NavGraphBuilder.vocaDetailsScreen(
    onNavigateBack: () -> Unit,
) {
    composable<VocaDetailsRoute> {
        VocaDetailsRoute (
            onNavigateBack = onNavigateBack,
        )
    }
}