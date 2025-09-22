package com.sandy.memorizingvoca.ui.feature.splash

import android.content.res.AssetManager
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme
import com.sandy.memorizingvoca.ui.theme.Pink80
import com.sandy.memorizingvoca.ui.theme.PyeoginGothic

@Composable
internal fun SplashRoute(
    onDownloadComplete: () -> Unit,
    onAppFinish: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel(),
) {
    val uiState by viewModel.splashUiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val assetManager: AssetManager = context.resources.assets
    LaunchedEffect(Unit) {
        viewModel.downloadVocabulary(assetManager)
    }
    LaunchedEffect(uiState) {
        if(uiState == SplashUiState.Complete) {
            onDownloadComplete()
        }
    }
    BackHandler(enabled = true) {
        onAppFinish()
    }
    SplashScreen()
}

@Composable
private fun SplashScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "영어,",
            fontFamily = PyeoginGothic,
            fontWeight = FontWeight.Black,
            fontSize = 72.sp,
        )
        Text(
            text = "결국 단어가 8할이다",
            fontFamily = PyeoginGothic,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
        )
        Spacer(modifier = modifier.height(16.dp))
        Text(
            modifier = modifier.background(color = Pink80).padding(horizontal = 4.dp),
            text = "영어 단어 암기 프로젝트",
            fontFamily = PyeoginGothic,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
        )
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    MemorizingVocaTheme {
        SplashScreen()
    }
}