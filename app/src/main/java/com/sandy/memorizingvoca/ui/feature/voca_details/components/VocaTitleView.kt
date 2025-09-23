package com.sandy.memorizingvoca.ui.feature.voca_details.components

import androidx.compose.foundation.background
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sandy.memorizingvoca.data.model.Vocabulary
import com.sandy.memorizingvoca.ui.theme.Gray30
import com.sandy.memorizingvoca.ui.theme.Pink10

@Composable
internal fun VocaTitleView(
    voca: Vocabulary,
    modifier: Modifier = Modifier,
) {
    val highlightColor = if (voca.highlighted) Pink10 else Color.Transparent
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp),
        ) {
            Text(
                modifier = modifier.alignByBaseline().background(
                    color = highlightColor,
                    shape = RectangleShape
                ),
                text = voca.word,
                fontWeight = FontWeight.Black,
                fontSize = 32.sp,

            )
            Spacer(modifier = modifier.width(8.dp))
            Text(
                modifier = modifier.alignByBaseline(),
                text = "[ˌɪˌlɛkˈtrɑnɪkəˌli]",
                color = Color.Gray,
            )
        }
        Spacer(modifier = modifier.height(8.dp))
        Text(
            modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp),
            text = voca.meaning,
            fontWeight = FontWeight.Normal,
        )
        Spacer(modifier = modifier.height(16.dp))
        HorizontalDivider(
            color = Gray30,
        )
    }
}

@Composable
@Preview
private fun VocaTitleViewPreview() {
    VocaTitleView(
        voca = Vocabulary(
            vocaId = 4,
            day = 1,
            word = "dictate",
            meaning = "[동] ① 명령하다, 지시하다 ② 받아쓰게 하다, 구술하다 [명] 명령, 지시[주로 pl.]",
        ),
    )
}