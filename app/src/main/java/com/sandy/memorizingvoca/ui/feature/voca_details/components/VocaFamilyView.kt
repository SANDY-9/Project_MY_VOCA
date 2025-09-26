package com.sandy.memorizingvoca.ui.feature.voca_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Done
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
import com.sandy.memorizingvoca.data.model.Word
import com.sandy.memorizingvoca.ui.extensions.noRippleClickable
import com.sandy.memorizingvoca.ui.theme.Pink100
import com.sandy.memorizingvoca.ui.theme.Pink40
import com.sandy.memorizingvoca.ui.theme.Pink80
import com.sandy.memorizingvoca.ui.theme.roundedCornerShape4
import com.sandy.memorizingvoca.utils.rememberTTSManager

@Composable
internal fun VocaFamilyView(
    title: String,
    item: List<Word>,
    modifier: Modifier = Modifier,
) {
    val ttsManager = rememberTTSManager()
    if(item.isNotEmpty()) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    top = 16.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 8.dp,
                )
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
            )
            Spacer(modifier = modifier.height(12.dp))
            item.forEach {
                WordItem(
                    word = it.word,
                    mean = it.mean,
                    onSpeak = {
                        ttsManager.speak(it.word)
                    }
                )
            }
        }
    }
}

@Composable
private fun WordItem(
    word: String,
    mean: String,
    onSpeak: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth().padding(bottom = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = modifier.size(5.dp).background(
                    color = Pink100,
                    shape = CircleShape,
                )
            )
            Spacer(modifier = modifier.width(8.dp))
            Text(
                modifier = modifier.noRippleClickable(onClick = onSpeak),
                text = word,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
            )
        }
        Spacer(modifier = modifier.height(4.dp))
        Text(
            text = mean,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 18.sp,
        )
    }
}

@Composable
@Preview
private fun VocaFamilyViewPreview() {
    VocaFamilyView(
        title = "유의어",
        item = listOf(
            Word(
                word = "withdrawal",
                mean = "탈퇴, 철수, 철회, 인출, 금단 증상"
            ),
            Word(
                word = "serious",
                mean = "심각한, 진지한, 정말, 중대한, 어려운"
            ),
        )

    )
}