package com.sandy.memorizingvoca.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.rememberNavController
import com.sandy.memorizingvoca.ui.feature.splash.navigation.SplashRoute
import com.sandy.memorizingvoca.ui.theme.Gray20
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlin.reflect.KClass

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
    val bottomNavVisible = appState.isBottomNavVisible()
    Scaffold(
        modifier = modifier.fillMaxSize(),
    ) { innerPadding ->
        Column(
            modifier = modifier.fillMaxSize().padding(innerPadding)
        ){
            MyAppNavGraph(
                modifier = modifier.weight(1f),
                navController = appState.navController,
                startDestination = startDestination,
                onAppFinish = onAppFinish,
            )
            if(bottomNavVisible) {
                MyAppBottomNav(
                    onItemClick = appState::navigateToDestination,
                    currentDestination = appState.currentDestination,
                )
            }
        }
    }
}

@Composable
private fun MyAppBottomNav(
    onItemClick: (MyAppBottomNavDestination) -> Unit,
    currentDestination: NavDestination?,
    items: List<MyAppBottomNavDestination> = MyAppBottomNavDestination.entries,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        HorizontalDivider(color = Gray20)
        Row (
            modifier = modifier
                .background(color = MaterialTheme.colorScheme.surface)
                .clip(RectangleShape),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = modifier.weight(1f))
            items.forEach { des ->
                val selected = currentDestination.isCurrentRoute(des.route)
                val color = if(selected) des.selectedColor else des.unselectedColor
                val icon = if(selected) des.selectedIcon else des.unselectedIcon
                Column(
                    modifier = modifier.size(55.dp).clickable {
                        onItemClick(des)
                    },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Icon(
                        modifier = modifier.size(24.dp),
                        imageVector = icon,
                        contentDescription = null,
                        tint = color,
                    )
                    Text(
                        text = des.title,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Normal,
                        color = color,
                    )
                }
                Spacer(modifier = modifier.weight(1f))
            }
        }
    }
}

private fun NavDestination?.isCurrentRoute(route: KClass<*>) = this?.hasRoute(route) ?: false

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