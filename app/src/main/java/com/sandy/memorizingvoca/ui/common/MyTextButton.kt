package com.sandy.memorizingvoca.ui.common

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sandy.memorizingvoca.ui.extensions.singleClick
import com.sandy.memorizingvoca.ui.theme.Pink100
import com.sandy.memorizingvoca.ui.theme.PyeoginGothic

@Composable
fun MyTextButton(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TextButton (
        modifier = modifier,
        onClick = singleClick { onClick() },
    ) {
        Text(
            text = title,
            fontFamily = PyeoginGothic,
            color = Pink100,
        )
    }
}