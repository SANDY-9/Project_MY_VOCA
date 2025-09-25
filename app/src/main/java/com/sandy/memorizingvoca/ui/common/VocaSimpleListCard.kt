package com.sandy.memorizingvoca.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sandy.memorizingvoca.ui.extensions.noRippleClickable
import com.sandy.memorizingvoca.ui.resources.VolumeUp
import com.sandy.memorizingvoca.ui.theme.Gray30
import com.sandy.memorizingvoca.ui.theme.Pink40
import com.sandy.memorizingvoca.ui.theme.Pink80

@Composable
fun VocaSimpleListCard(
    word: String,
    meaning: String,
    highlighted: Boolean,
    onClick: () -> Unit,
    onSpeak: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val highlightColor = if (highlighted) Pink40 else Color.Transparent
    Column(
        modifier = modifier
            .background(
                color = highlightColor,
            )
            .clickable(onClick = onClick),
    ) {
        Spacer(modifier = modifier.height(16.dp))
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                modifier = modifier.fillMaxWidth(0.5f),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = word,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                )
                Spacer(modifier = modifier.width(8.dp))
                Icon(
                    modifier = modifier.noRippleClickable(onClick = onSpeak),
                    imageVector = Icons.AutoMirrored.Rounded.VolumeUp,
                    contentDescription = null,
                    tint = Pink80,
                )
            }
            Spacer(modifier = modifier.width(4.dp))
            Text(
                modifier = modifier.fillMaxWidth(),
                text = meaning,
                fontWeight = FontWeight.Medium,
                fontSize = 13.sp,
            )
        }
        Spacer(modifier = modifier.height(16.dp))
        HorizontalDivider(color = Gray30)
    }
}

@Composable
@Preview
private fun Preview() {
    VocaSimpleListCard(
        word = "take into",
        meaning = "① -을 생각해 내다 ② -을 해결하다[알아내다] ③ -을 계산[산출]하다 ④ 운동하다 ⑤ (일이) 잘 풀리다",
        highlighted = false,
        onClick = {},
        onSpeak = {},
    )
}