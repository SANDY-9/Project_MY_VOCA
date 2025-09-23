package com.sandy.memorizingvoca.ui.feature.voca_details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sandy.memorizingvoca.data.model.ExampleSentence
import com.sandy.memorizingvoca.ui.theme.Gray30

@Composable
internal fun VocaExampleView(
    item: List<ExampleSentence>,
    modifier: Modifier = Modifier,
) {
    if(item.isNotEmpty()) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp,)
        ) {
            Text(
                text = "예문",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
            )
            item.forEach {
                ExampleItem(
                    htmlSentence = it.highlightedEmphWordsSentence,
                    htmlMean = it.highlightedEmphWordsMean,
                )
            }
        }
    }
}

@Composable
private fun ExampleItem(
    htmlSentence: String,
    htmlMean: String,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .fillMaxWidth().padding(top = 16.dp),
    ) {
        Text(
            text = AnnotatedString.fromHtml(htmlSentence),
            fontSize = 16.sp,
        )
        Spacer(modifier = modifier.height(4.dp))
        Text(
            text = AnnotatedString.fromHtml(htmlMean),
            color = Color.DarkGray,
        )
        Spacer(modifier = modifier.height(16.dp))
        HorizontalDivider(
            color = Gray30,
        )
    }
}

@Composable
@Preview
private fun VocaExampleViewPreview() {
    VocaExampleView(
        item = listOf(
            ExampleSentence(
                sentence = "As the U.S. withdrew a majority of its troops from Afghanistan in August, the Taliban have once again taken power in the country.",
                mean = "미국이 지난 8월 아프가니스탄에서 대부분의 군대를 철수하자 탈레반이 또 다시 집권했다.",
                emphWords = emptyList(),
            ),
            ExampleSentence(
                sentence = "There is no earnest conversation.",
                mean = "솔직한 대화도 없고",
                emphWords = emptyList(),
            ),
            ExampleSentence(
                sentence = "However, Boone withdrew from the show after entering the Top 24 to consider his career.",
                mean = "하지만, Boone은 그의 직업을 고려하여 탑 24에 들어간 후 그 프로그램에서 기권했습니다.",
                emphWords = emptyList(),
            ),
        )

    )
}