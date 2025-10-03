package com.sandy.memorizingvoca.ui.feature.voca_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sandy.memorizingvoca.data.model.Word
import com.sandy.memorizingvoca.ui.theme.roundedCornerShape4

@Composable
internal fun VocaGrammarView(
    item: Map<String, List<Word>>,
    modifier: Modifier = Modifier,
) {
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
            item.forEach { (title, wordList) ->
                VocaItemFlowRow(
                    title = title,
                    item = wordList,
                )
                Spacer(modifier = modifier.height(8.dp))
            }
        }
    }
}

@Composable
private fun VocaItemFlowRow(
    title: String,
    item: List<Word>,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Box(modifier = modifier.width(56.dp)) {
            Text(
                modifier = modifier.padding(top = 2.dp).background(
                    color = MaterialTheme.colorScheme.tertiary,
                    shape = roundedCornerShape4,
                ).padding(horizontal = 4.dp),
                text = title,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
            )
        }
        FlowRow(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            item.forEach {
                VocaItem(
                    title = it.mean,
                    word = it.word,
                )
            }
        }
    }
}

@Composable
private fun VocaItem(
    title: String,
    word: String,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            modifier = modifier.alignByBaseline(),
            text = title,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSecondary,
        )
        Text(
            modifier = modifier.alignByBaseline(),
            text = word,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
        )
    }
}

@Composable
@Preview
private fun VocaGrammarViewPreview() {
    VocaGrammarView(
        item = mapOf(
            "동사" to listOf(
                Word(
                    word = "earnest",
                    mean = "기본"
                ),
                Word(
                    word = "earnested",
                    mean = "과거"
                ),
                Word(
                    word = "earnested",
                    mean = "과거분사"
                ),
                Word(
                    word = "earnesting",
                    mean = "현재분사"
                ),
                Word(
                    word = "earnests",
                    mean = "3인칭단수"
                ),
            ),
            "명사" to listOf(
                Word(
                    word = "earnest",
                    mean = "기본"
                ),
            ),
            "형용사" to listOf(
                Word(
                    word = "earnest",
                    mean = "기본"
                ),
                Word(
                    word = "more earnest, earnester",
                    mean = "비교급"
                ),
                Word(
                    word = "most earnest, earnestest",
                    mean = "최상급"
                ),
            )
        )
    )
}