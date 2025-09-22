package com.sandy.memorizingvoca.ui.feature.splash

import android.content.res.AssetManager
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun SplashRoute(
    onAppFinish: () -> Unit,
) {
    BackHandler(enabled = true) {
        onAppFinish()
    }
    SplashScreen()
}

@Composable
private fun SplashScreen() {

}