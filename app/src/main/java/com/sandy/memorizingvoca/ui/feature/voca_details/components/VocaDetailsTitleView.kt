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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sandy.memorizingvoca.ui.extensions.noRippleClickable
import com.sandy.memorizingvoca.ui.theme.Gray30
import com.sandy.memorizingvoca.ui.theme.Pink80
import com.sandy.memorizingvoca.utils.rememberTTSManager

@Composable
internal fun VocaDetailsTitleView(
    word: String,
    meaning: String,
    highlighted: Boolean,
    pron: String = "",
    modifier: Modifier = Modifier,
)  {
    val ttsManager = rememberTTSManager()
    val highlightColor = if (highlighted) Pink80 else Color.Transparent
    Column(
        modifier = modifier.fillMaxWidth().background(color = MaterialTheme.colorScheme.surface),
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        ) {
            Text(
                modifier = modifier
                    .alignByBaseline()
                    .background(
                        color = highlightColor,
                        shape = RectangleShape
                    ).noRippleClickable{
                        ttsManager.speak(word)
                    },
                text = word,
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
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = meaning,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
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
    VocaDetailsTitleView(
        word = "dictate",
        meaning = "[동] ① 명령하다, 지시하다 ② 받아쓰게 하다, 구술하다 [명] 명령, 지시[주로 pl.]",
        highlighted = true,
    )
}