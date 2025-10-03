package com.sandy.memorizingvoca.ui.common

import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.sandy.memorizingvoca.ui.extensions.noRippleClickable
import com.sandy.memorizingvoca.ui.resources.Star
import com.sandy.memorizingvoca.ui.resources.StarOutline

@Composable
fun VocaBookmarkButton(
    bookmarked: Boolean,
    onBookmarkChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Icon(
        modifier = modifier.noRippleClickable {
            onBookmarkChange(!bookmarked)
        },
        imageVector = if(bookmarked) Icons.Rounded.Star else Icons.Rounded.StarOutline,
        contentDescription = null,
        tint = if(bookmarked) MaterialTheme.colorScheme.primary else Color.LightGray,
    )
}

@Composable
@Preview
private fun Preview() {
    var bookmarked by remember { mutableStateOf(false) }
    VocaBookmarkButton(
        bookmarked = bookmarked,
        onBookmarkChange = { bookmarked = !bookmarked },
    )
}