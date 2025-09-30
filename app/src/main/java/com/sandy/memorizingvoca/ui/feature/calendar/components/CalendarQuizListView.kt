package com.sandy.memorizingvoca.ui.feature.calendar.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sandy.memorizingvoca.data.model.Date
import com.sandy.memorizingvoca.data.model.VocaQuiz
import com.sandy.memorizingvoca.ui.extensions.percentageColor
import com.sandy.memorizingvoca.ui.theme.Gray30
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme
import com.sandy.memorizingvoca.ui.theme.Pink100
import com.sandy.memorizingvoca.ui.theme.PyeoginGothic
import java.time.LocalDateTime

@Composable
internal fun CalendarQuizListView(
    date: Date,
    quizList: List<VocaQuiz>,
    onDeleteListClick: () -> Unit,
    onItemClick: (String) -> Unit,
    onDeleteItemClick: (VocaQuiz) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        stickyHeader {
            QuizListHeader(
                year = date.year,
                month = date.month,
                day = date.day,
                dayOfWeekName = date.dayOfWeek.fullName,
                onDeleteListClick = onDeleteListClick,
            )
            HorizontalDivider(color = Gray30)
        }
        itemsIndexed(
            items = quizList
        ) { index, it ->
            QuizListItem(
                quiz = it,
                onItemClick = { onItemClick(it.date) },
                onDeleteClick = { onDeleteItemClick(it) },
            )
        }
    }
}

@Composable
private fun QuizListHeader(
    year: Int,
    month: Int,
    day: Int,
    dayOfWeekName: String,
    onDeleteListClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(start = 16.dp)
    ) {
        Text(
            modifier = modifier.align(Alignment.CenterStart),
            text = "${year}년 ${month}월 ${day}일 ${dayOfWeekName}",
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
        )
        TextButton(
            modifier = modifier.align(Alignment.CenterEnd),
            onClick = onDeleteListClick,
        ) {
            Text(
                text = "전체삭제",
                fontFamily = PyeoginGothic,
                color = Pink100,
            )
        }
        Spacer(modifier = modifier.width(8.dp),)
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
            .clickable(onClick = onItemClick),
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
                        color = Color.DarkGray,
                    )
                }
                Spacer(modifier = modifier.height(4.dp))
                Text(
                    text = quiz.timeString,
                    fontSize = 12.sp,
                    color = Color.Gray,
                )
            }
            TextButton(
                onClick = onDeleteClick,
            ) {
                Text(
                    text = "삭제",
                    fontFamily = PyeoginGothic,
                    color = Pink100,
                )
            }
        }
        HorizontalDivider(color = Gray30)
    }
}

@Preview
@Composable
private fun CalendarQuizListViewPreview() {
    MemorizingVocaTheme {
        CalendarQuizListView(
            date = Date(),
            quizList = listOf(
                VocaQuiz(
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
                ),
            ),
            onDeleteListClick = {},
            onItemClick = {},
            onDeleteItemClick = {},
        )
    }
}
