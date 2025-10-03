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
import com.sandy.memorizingvoca.ui.resources.AutoFixHigh

@Composable
fun VocaHighlightButton(
    highlighted: Boolean,
    onHighlightChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Icon(
        modifier = modifier.noRippleClickable {
            onHighlightChange(!highlighted)
        },
        imageVector = if(highlighted) Icons.Rounded.AutoFixHigh else Icons.Outlined.AutoFixHigh,
        contentDescription = null,
        tint = if(highlighted) MaterialTheme.colorScheme.primary else Color.LightGray,
    )
}

@Composable
@Preview
private fun Preview() {
    var highlighted by remember { mutableStateOf(false) }
    VocaHighlightButton(
        highlighted = highlighted,
        onHighlightChange = { highlighted = !highlighted },
    )
}