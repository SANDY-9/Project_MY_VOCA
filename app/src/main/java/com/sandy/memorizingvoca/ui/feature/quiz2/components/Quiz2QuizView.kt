package com.sandy.memorizingvoca.ui.feature.quiz2.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sandy.memorizingvoca.data.model.Vocabulary
import com.sandy.memorizingvoca.ui.feature.quiz2.GameSetStatus
import com.sandy.memorizingvoca.ui.feature.quiz2.VocaCardState
import com.sandy.memorizingvoca.ui.feature.quiz2.VocaCardType
import com.sandy.memorizingvoca.ui.theme.DarkRedTransparent
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme
import com.sandy.memorizingvoca.ui.theme.Pink40
import com.sandy.memorizingvoca.ui.theme.PyeoginGothic
import com.sandy.memorizingvoca.ui.theme.roundedCornerShape10

@Composable
internal fun Quiz2QuizView(
    cardList: List<VocaCardState>,
    status: GameSetStatus,
    firstSelectedCard: VocaCardState?,
    secondSelectedCard: VocaCardState?,
    onCardSelect: (VocaCardState) -> Unit,
    onSpeak: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val haptic = LocalHapticFeedback.current
    FlowRow (
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(
                start = 12.dp,
                end = 12.dp,
                bottom = 12.dp,
            ),
        maxItemsInEachRow = 3,
        maxLines = 4,
    ) {
        cardList.forEach { item ->
            val selected = firstSelectedCard == item || secondSelectedCard == item
            Box(
                modifier = modifier.fillMaxWidth(0.333f).fillMaxHeight(0.249f),
            ) {
                when(item.type) {
                    VocaCardType.WORD -> VocaWordCard(
                        text = item.text,
                        selected = selected,
                        status = status,
                        isAnswered = item.isAnswered,
                        onSpeak = onSpeak,
                        onClick = {
                            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                            onCardSelect(item)
                        },
                    )
                    VocaCardType.MEANING -> VocaMeaningCard(
                        text = item.text,
                        selected = selected,
                        status = status,
                        isAnswered = item.isAnswered,
                        onClick = {
                            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                            onCardSelect(item)
                        },
                    )
                }
            }
        }
    }
}

private val selectedBackgroundModifier = Modifier.background(
    color = Pink40,
    shape = roundedCornerShape10,
)

private val unselectedBackgroundModifier = Modifier
    .background(
        color = Color.White,
        shape = roundedCornerShape10,
    )
    .border(
        width = 1.dp,
        color = Color.LightGray,
        shape = roundedCornerShape10,
    )


@Composable
private fun VocaWordCard(
    text: String,
    selected: Boolean,
    status: GameSetStatus,
    isAnswered: Boolean,
    onClick: () -> Unit,
    onSpeak: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    AnimatedVisibility(
        visible = !isAnswered,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Box(
            modifier = modifier
                .padding(4.dp)
                .fillMaxSize()
                .then(
                    if (selected) selectedBackgroundModifier
                    else unselectedBackgroundModifier
                )
                .clip(roundedCornerShape10)
                .clickable {
                    onSpeak(text)
                    onClick()
                }
                .padding(8.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                modifier = modifier.fillMaxWidth(),
                text = text,
                fontWeight = FontWeight.SemiBold,
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                color = Color.DarkGray,
            )
            if(status == GameSetStatus.INCORRECTED && selected) {
                Text(
                    text = "X",
                    fontWeight = FontWeight.Black,
                    fontSize = 100.sp,
                    textAlign = TextAlign.Center,
                    color = DarkRedTransparent,
                )
            }
        }
    }
}

@Composable
private fun VocaMeaningCard(
    text: String,
    selected: Boolean,
    status: GameSetStatus,
    isAnswered: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AnimatedVisibility(
        visible = !isAnswered,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Box(
            modifier = modifier
                .padding(4.dp)
                .fillMaxSize()
                .then(
                    if (selected) selectedBackgroundModifier
                    else unselectedBackgroundModifier
                )
                .clip(roundedCornerShape10)
                .clickable(onClick = onClick)
                .padding(10.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = text,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 18.sp,
                style = TextStyle.Default.copy(
                    lineBreak = LineBreak.Simple,
                    fontFamily = PyeoginGothic,
                ),
            )
            if(status == GameSetStatus.INCORRECTED && selected) {
                Text(
                    text = "X",
                    fontWeight = FontWeight.Black,
                    fontSize = 100.sp,
                    textAlign = TextAlign.Center,
                    color = DarkRedTransparent,
                )
            }
        }
    }
}

@Preview
@Composable
private fun Quiz2QuizViewPreview() {
    var card1 by remember { mutableStateOf<VocaCardState?>(null) }
    var card2 by remember { mutableStateOf<VocaCardState?>(null) }
    MemorizingVocaTheme {
        Quiz2QuizView(
            status = GameSetStatus.NONE,
            firstSelectedCard = card1,
            secondSelectedCard = card2,
            onCardSelect = {
                if(card1 == null) {
                    card1 = it
                }
                else {
                    card2 = it
                }
            },
            onSpeak = {},
            cardList = listOf(
                VocaCardState(
                    type = VocaCardType.WORD,
                    text = "advocate",
                    voca = Vocabulary(
                        vocaId = 2,
                        day = 1,
                        word = "advocate",
                        meaning = "[동] ① 지지하다, 옹호하다 ② 주장하다 [명] 지지자, 옹호자",
                    ),
                ),
                VocaCardState(
                    type = VocaCardType.WORD,
                    text = "affluent",
                    voca = Vocabulary(
                        vocaId = 3,
                        day = 1,
                        word = "affluent",
                        meaning = "[형] ① 풍부한 ② 부유한"
                    ),
                ),
                VocaCardState(
                    type = VocaCardType.MEANING,
                    text = "ing 하는 데 어려움[곤란]을 겪다",
                    voca = Vocabulary(
                        vocaId = 5,
                        day = 1,
                        word = "have trouble[difficulty] (in)",
                        meaning = "ing 하는 데 어려움[곤란]을 겪다",
                    ),
                ),
                VocaCardState(
                    type = VocaCardType.WORD,
                    text = "have trouble[difficulty] (in)",
                    voca = Vocabulary(
                        vocaId = 5,
                        day = 1,
                        word = "have trouble[difficulty] (in)",
                        meaning = "ing 하는 데 어려움[곤란]을 겪다",
                    ),
                ),
                VocaCardState(
                    type = VocaCardType.MEANING,
                    text = "[동] ① 지지하다, 옹호하다 ② 주장하다 [명] 지지자, 옹호자",
                    voca = Vocabulary(
                        vocaId = 2,
                        day = 1,
                        word = "advocate",
                        meaning = "[동] ① 지지하다, 옹호하다 ② 주장하다 [명] 지지자, 옹호자",
                    ),
                ),
                VocaCardState(
                    type = VocaCardType.MEANING,
                    text = "[형] ① 풍부한 ② 부유한",
                    voca = Vocabulary(
                        vocaId = 3,
                        day = 1,
                        word = "affluent",
                        meaning = "[형] ① 풍부한 ② 부유한",
                    ),
                ),
            )
        )
    }
}
