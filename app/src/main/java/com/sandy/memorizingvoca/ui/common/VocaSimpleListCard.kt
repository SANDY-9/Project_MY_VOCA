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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.sandy.memorizingvoca.ui.theme.PyeoginGothic

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
                    modifier = modifier.noRippleClickable(onClick = onSpeak),
                    text = word,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    style = TextStyle.Default.copy(
                        lineBreak = LineBreak.Heading,
                        fontFamily = PyeoginGothic,
                    ),
                )
            }
            Spacer(modifier = modifier.width(12.dp))
            Text(
                modifier = modifier.fillMaxWidth(),
                text = meaning,
                fontWeight = FontWeight.Normal,
                fontSize = 13.sp,
                lineHeight = 18.sp,
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