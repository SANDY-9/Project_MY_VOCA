package com.sandy.memorizingvoca.ui.feature.quiz1.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sandy.memorizingvoca.ui.extensions.noRippleClickable
import com.sandy.memorizingvoca.ui.feature.quiz1.AnswerState
import com.sandy.memorizingvoca.ui.theme.DarkBlue
import com.sandy.memorizingvoca.ui.theme.DarkRed
import com.sandy.memorizingvoca.ui.theme.Pink100
import com.sandy.memorizingvoca.ui.theme.Pink40
import com.sandy.memorizingvoca.ui.theme.Pink80
import com.sandy.memorizingvoca.ui.theme.roundedCornerShape16
import com.sandy.memorizingvoca.utils.rememberTTSManager

@Composable
internal fun Quiz1QuizView(
    questionNumTitle: String,
    questionWord: String,
    answerState: AnswerState,
    options: List<String>,
    answerIndex: Int,
    onOptionSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val ttsManager = rememberTTSManager()
    LaunchedEffect(answerState) {
        if(answerState != AnswerState.NONE) {
            ttsManager.speak(questionWord)
        }
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = modifier.weight(1f))
        Text(
            text = questionNumTitle,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.DarkGray,
        )
        Spacer(modifier = modifier.height(16.dp))
        Text(
            modifier = modifier.noRippleClickable(
              onClick = {
                  ttsManager.speak(questionWord)
              }
            ),
            text = questionWord,
            fontWeight = FontWeight.Black,
            textAlign = TextAlign.Center,
            fontSize = 45.sp,
        )
        Spacer(modifier = modifier.height(40.dp))
        OptionsList(
            answerState = answerState,
            options = options,
            answerIndex = answerIndex,
            onOptionSelect = onOptionSelect,
        )
        Box(
            modifier = modifier.weight(1f),
            contentAlignment = Alignment.Center,
        ) {
            if(answerState == AnswerState.CORRECT) {
                AnswerResultCorrect()
            }
            if(answerState == AnswerState.INCORRECT) {
                AnswerResultIncorrect()
            }
        }
    }
}

@Composable
private fun OptionsList(
    answerState: AnswerState,
    options: List<String>,
    answerIndex: Int,
    onOptionSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(0.85f),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        options.forEachIndexed { index, option ->
            when(answerState) {
                AnswerState.CORRECT, AnswerState.INCORRECT -> OptionsAnswerItem(
                    text = option,
                    isAnswered = index == answerIndex,
                )
                else -> OptionsItem(
                    text = option,
                    onSelect = { onOptionSelect(index) },
                )
            }
        }
    }
}

@Composable
private fun OptionsAnswerItem(
    text: String,
    isAnswered: Boolean,
    modifier: Modifier = Modifier,
) {
    val backgroundColor = if(isAnswered) Pink80 else Color.LightGray
    val borderColor = if(isAnswered) Pink100 else Color.Transparent
    val textDecoration = if(isAnswered) TextDecoration.None else TextDecoration.LineThrough
    val fontWeight = if(isAnswered) FontWeight.Bold else FontWeight.Medium
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(
                color = backgroundColor,
                shape = roundedCornerShape16,
            )
            .border(
                width = 2.dp,
                color = borderColor,
                shape = roundedCornerShape16,
            )
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            fontWeight = fontWeight,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textDecoration = textDecoration,
        )
    }
}

@Composable
private fun OptionsItem(
    text: String,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(
                color = Pink40,
                shape = roundedCornerShape16,
            )
            .clip(roundedCornerShape16)
            .clickable(onClick = onSelect)
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
private fun AnswerResultIncorrect(
    modifier: Modifier = Modifier,
) {
    AnimatedVisibility(true) {
        Column (
            modifier = modifier.animateContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "X",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = DarkRed,
            )
            Text(
                text = "틀렸습니다!",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = DarkRed,
            )
        }
    }
}

@Composable
private fun AnswerResultCorrect(
    modifier: Modifier = Modifier,
) {
    Column (
        modifier = modifier.animateContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "O",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = DarkBlue,
        )
        Text(
            text = "정답입니다!",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = DarkBlue,
        )
    }
}


@Composable
@Preview
private fun Quiz1QuizViewPreview() {
    var answerState by remember { mutableStateOf(AnswerState.SOLVING_QUESTIONS) }
    Quiz1QuizView(
        questionNumTitle = "02.",
        questionWord = "respect",
        answerState = answerState,
        options = listOf(
            "[형] 눈에 잘 띄는, 뚜렷한",
            "[동] 존경하다 [명] ① 존경 ② (측)면",
            "[동] 명시하다, 구체화하다",
            "① -을 생각해 내다 ② -을 해결하다[알아내다] ③ -을 계산[산출]하다 ④ 운동하다 ⑤ (일이) 잘 풀리다"
        ),
        answerIndex = 1,
        onOptionSelect = { selectedIndex ->
            answerState = if(selectedIndex == 1) AnswerState.CORRECT else AnswerState.INCORRECT
        }
    )
}