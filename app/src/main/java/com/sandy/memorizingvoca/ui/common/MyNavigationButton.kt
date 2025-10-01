package com.sandy.memorizingvoca.ui.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.sandy.memorizingvoca.ui.extensions.singleClick

@Composable
fun MyNavigationButton(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    IconButton(
        onClick = singleClick { onNavigateBack() },
    ) {
        Icon(
            modifier = modifier,
            imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
            contentDescription = null,
            tint = Color.Black,
        )
    }
}