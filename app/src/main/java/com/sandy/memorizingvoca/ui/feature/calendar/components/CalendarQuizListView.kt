package com.sandy.memorizingvoca.ui.feature.calendar.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sandy.memorizingvoca.data.model.VocaQuiz
import com.sandy.memorizingvoca.ui.common.MyTextButton
import com.sandy.memorizingvoca.ui.extensions.percentageColor
import com.sandy.memorizingvoca.ui.extensions.singleClick
import com.sandy.memorizingvoca.ui.theme.Gray30
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme
import androidx.compose.material3.MaterialTheme

@Composable
internal fun CalendarQuizListView(
    quizList: List<VocaQuiz>,
    onItemClick: (String) -> Unit,
    onDeleteItemClick: (VocaQuiz) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = modifier.fillMaxWidth(),
        ) {
            items(quizList) {
                QuizListItem(
                    quiz = it,
                    onItemClick = {
                        onItemClick(it.date)
                    },
                    onDeleteClick = { onDeleteItemClick(it) },
                )
            }
        }
        Column(
            modifier = modifier.weight(1f).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = if(quizList.isEmpty()) "학습한 내용이 아직 없어요." else "",
                color = Color.Gray,
            )
        }
    }
}

@Composable
private fun QuizListItem(
    quiz: VocaQuiz,
    onItemClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .singleClick(onClick = onItemClick),
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    top = 2.dp,
                    bottom = 2.dp,
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = modifier.weight(1f),
            ) {
                Row {
                    Text(
                        text = quiz.quizName,
                        fontWeight = FontWeight.Medium,
                    )
                    Spacer(modifier = modifier.width(8.dp))
                    Text(
                        modifier = modifier.background(
                            color = quiz.correctPercentage.percentageColor(),
                            shape = CircleShape,
                        )
                            .padding(horizontal = 5.dp),
                        text = "${quiz.correctPercentage}%",
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSecondary,
                    )
                }
                Spacer(modifier = modifier.height(4.dp))
                Text(
                    text = quiz.timeString,
                    fontSize = 12.sp,
                    color = Color.Gray,
                )
            }
            MyTextButton(
                title = "삭제",
                onClick = onDeleteClick,
            )
        }
        HorizontalDivider(color = MaterialTheme.colorScheme.outline)
    }
}

@Preview
@Composable
private fun CalendarQuizListViewPreview() {
    MemorizingVocaTheme {
        CalendarQuizListView(
            quizList = listOf(
                /*VocaQuiz(
                    date = LocalDateTime.now().toString(),
                    day = 3,
                    wrongCount = 0,
                    totalCount = 10,
                ),
                VocaQuiz(
                    date = LocalDateTime.now().plusHours(1).toString(),
                    day = 0,
                    wrongCount = 3,
                    totalCount = 10,
                ),
                VocaQuiz(
                    date = LocalDateTime.now().plusDays(1).toString(),
                    day = 10,
                    wrongCount = 1,
                    totalCount = 10,
                ),
                VocaQuiz(
                    date = LocalDateTime.now().plusDays(2).toString(),
                    day = 10,
                    wrongCount = 7,
                    totalCount = 10,
                ),*/
            ),
            onItemClick = {},
            onDeleteItemClick = {},
        )
    }
}
