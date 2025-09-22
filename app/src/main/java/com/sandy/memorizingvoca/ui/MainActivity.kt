package com.sandy.memorizingvoca.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.sandy.memorizingvoca.ui.feature.splash.navigation.SplashRoute
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val myAppState = rememberMyAppState()
            MemorizingVocaTheme {
                MainApp(
                    appState = myAppState,
                    startDestination = SplashRoute,
                    onAppFinish = ::finishAffinity,
                )
            }
        }
    }
}

@Composable
private fun MainApp(
    appState: MyAppState,
    startDestination: Any,
    onAppFinish: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
    ) { innerPadding ->
        MyAppNavGraph(
            modifier = modifier.padding(innerPadding),
            navController = appState.navController,
            startDestination = startDestination,
            onAppFinish = onAppFinish,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MainAppPreview() {
    MainApp(
        appState = MyAppState(
            navController = rememberNavController(),
        ),
        startDestination = "splash",
        onAppFinish = {},
    )
}