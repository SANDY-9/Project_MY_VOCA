package com.sandy.memorizingvoca.ui.extensions

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.sandy.memorizingvoca.ui.theme.Pink100
import com.sandy.memorizingvoca.ui.theme.roundedCornerShape6

fun Modifier.clickableSelectOutline(
    selected: Boolean,
    onClick: () -> Unit,
) = this.then(
    if(selected) {
        Modifier
            .border(
                width = 1.dp,
                color = Pink100,
                shape = roundedCornerShape6,
            )
            .padding(2.dp)
            .clip(roundedCornerShape6)
            .clickable(onClick = onClick)
    }
    else {
        Modifier
            .padding(1.dp)
            .clickable(onClick = onClick)
    }
)