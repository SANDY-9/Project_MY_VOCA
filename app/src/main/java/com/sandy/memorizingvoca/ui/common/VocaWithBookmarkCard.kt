package com.sandy.memorizingvoca.ui.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sandy.memorizingvoca.ui.extensions.noRippleClickable
import com.sandy.memorizingvoca.ui.theme.Gray30
import com.sandy.memorizingvoca.ui.theme.Pink40
import com.sandy.memorizingvoca.ui.theme.Pink80
import com.sandy.memorizingvoca.ui.theme.PyeoginGothic

@Composable
fun VocaWithBookmarkCard (
    word: String,
    meaning: String,
    highlighted: Boolean,
    bookmarked: Boolean,
    onSpeak: () -> Unit,
    onClick: () -> Unit,
    onBookmarkChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    blindMode: Boolean = false,
) {
    val highlightColor = if (highlighted) Pink80 else MaterialTheme.colorScheme.background
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = highlightColor,
            )
            .clickable(onClick = onClick)
            .padding(top = 16.dp),
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = modifier.fillMaxWidth(0.9f)
            ) {
                Text(
                    modifier = modifier.noRippleClickable(onClick = onSpeak),
                    text = word,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                )
            }
            Spacer(modifier = modifier.weight(1f))
            VocaBookmarkButton(
                bookmarked = bookmarked,
                onBookmarkChange = onBookmarkChange,
            )
        }
        AnimatedVisibility(!blindMode) {
            Text(
                modifier = modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 8.dp,
                ),
                text = meaning,
                fontWeight = FontWeight.Normal,
                fontSize = 13.sp,
                lineHeight = 18.sp,
            )
        }
        Spacer(modifier = modifier.height(16.dp))
        HorizontalDivider(
            color = Gray30,
        )
    }
}

@Preview
@Composable
private fun VocaWithBookmarkCardPreview() {
    VocaWithBookmarkCard(
        word = "pale",
        meaning = "① (안색이) 창백한 ② (색이) 엷은, 옅은 ③ (빛이) 약한 [동] ① (안색이) 창백해지다 ② 색이 옅어지다 ③ (빛이) 약해지다",
        highlighted = false,
        bookmarked = true,
        onSpeak = {},
        onClick = {},
        onBookmarkChange = {},
    )
}
