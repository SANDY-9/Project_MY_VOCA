package com.sandy.memorizingvoca.ui.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.sandy.memorizingvoca.ui.extensions.noRippleClickable
import com.sandy.memorizingvoca.ui.theme.Pink100

@Composable
fun VocaBookmarkButton(
    bookmarked: Boolean,
    onBookmarkChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    var checked by remember { mutableStateOf(bookmarked) }
    val clickEvent = {
        checked = !checked
        onBookmarkChange(checked)
    }
    when {
        checked -> Icon(
            modifier = modifier.noRippleClickable(clickEvent),
            imageVector = Icons.Rounded.CheckCircle,
            contentDescription = null,
            tint = Pink100,
        )
        else -> Icon(
            modifier = modifier.noRippleClickable(clickEvent),
            imageVector = Icons.Outlined.CheckCircle,
            contentDescription = null,
            tint = Color.LightGray,
        )
    }
}

@Composable
@Preview
private fun Preview() {
    VocaBookmarkButton(
        bookmarked = false,
        onBookmarkChange = {},
    )
}