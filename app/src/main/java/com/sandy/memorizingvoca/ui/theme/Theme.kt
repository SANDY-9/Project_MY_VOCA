package com.sandy.memorizingvoca.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = PinkDark100,
    onSecondary = Color.LightGray,
    onSurfaceVariant = Color.DarkGray,
    outline = Color.DarkGray,
    outlineVariant = DarkGray100,
    tertiary = PinkDark40,
    secondary = PinkDark80,
    onPrimaryContainer = Gray20,
    primaryContainer = PinkDark10,
    surfaceVariant = Color.DarkGray,
    surfaceDim = PinkDark90,
    surfaceContainer = CloudYellow,
    onBackground = Color(0xFFFDFDFD),
    onSurface = Color(0xFFFDFDFD),
    error = DarkRedInverse,
    onError = DarkRed,
    errorContainer = DarkBlueInverse,
    onErrorContainer = DarkBlue,
)

private val LightColorScheme = lightColorScheme(
    primary = Pink100,
    surface = Color.White, //Pink10,
    background = Color.White,
    onSecondary = Color.DarkGray,
    onSurfaceVariant = Color.LightGray,
    outline = Gray30,
    primaryContainer = Pink10,
    tertiary = Pink40,
    secondary = Pink80,
    outlineVariant = Gray20,
    surfaceVariant = Gray30,
    onPrimaryContainer = Color.Gray,
    surfaceContainer = PastelYellow,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    surfaceDim = Pink90,
    error = DarkRed,
    onError = DarkRed,
    errorContainer = DarkBlue,
    onErrorContainer = DarkBlue,
)

@Composable
fun MemorizingVocaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    SideEffect {
        val window = (view.context as Activity).window
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}