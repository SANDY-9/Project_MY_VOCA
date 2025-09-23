package com.sandy.memorizingvoca.ui.feature.voca_details.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sandy.memorizingvoca.data.model.Vocabulary
import com.sandy.memorizingvoca.ui.feature.voca_details.VocaDetailsRoute
import com.sandy.memorizingvoca.ui.feature.voca_list.VocaListRoute
import com.sandy.memorizingvoca.ui.feature.voca_list.navigation.VocaListRoute
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