package com.sandy.memorizingvoca.ui.feature.calendar.components.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sandy.memorizingvoca.data.model.Calendar
import com.sandy.memorizingvoca.data.model.Date
import com.sandy.memorizingvoca.data.model.VocaQuiz
import com.sandy.memorizingvoca.ui.extensions.clickableSelectOutline
import com.sandy.memorizingvoca.ui.extensions.percentageColor
import com.sandy.memorizingvoca.ui.theme.Gray30
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme
import com.sandy.memorizingvoca.utils.DateUtils
import java.time.LocalDate
import java.time.LocalDateTime

@Composable
internal fun ExpandCalendar(
    calendar: Calendar,
    today: Date,
    selectDate: Date?,
    quizCalendar: Map<Date, List<VocaQuiz>>,
    onQuizItemClick: (String) -> Unit,
    onDateSelect: (Date) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column (
        modifier = modifier.fillMaxSize(),
    ) {
        calendar.days.forEachIndexed { week, days ->
            Row(
                modifier = Modifier.weight(1f),
            ) {
                days.forEach { date ->
                    val quizList = quizCalendar[date] ?: emptyList()
                    FlowColumn (
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clickableSelectOutline(
                                selected = selectDate == date,
                                onClick = { onDateSelect(date) },
                            ),
                    ) {
                        DateHeader(
                            date = date,
                            isToday = date == today,
                            otherMonth = calendar.otherMonth[date] ?: false,
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        quizList.forEach { quiz ->
                            ExpandCalendarItem(
                                quiz = quiz,
                                onItemClick = {
                                    onDateSelect(date)
                                    onQuizItemClick(quiz.date)
                                },
                            )
                        }
                    }
                }
            }
            if(week != calendar.days.lastIndex) {
                HorizontalDivider(color = Gray30)
            }
        }
    }
}

@Composable
private fun ExpandCalendarItem(
    quiz: VocaQuiz,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 2.dp)
            .background(
                color = quiz.correctPercentage.percentageColor(),
            )
            .clickable(onClick = onItemClick)
            .padding(
                vertical = 2.dp,
                horizontal = 4.dp,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = quiz.quizName,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = Color.DarkGray,
            fontSize = 12.sp,
        )
    }
}

@Preview
@Composable
private fun ExpandCalendarPreview() {
    val date = Date()
    MemorizingVocaTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            ExpandCalendar(
                calendar = Calendar(
                    year = date.year,
                    month = date.month,
                    days = DateUtils.createCalendar(
                        year = date.year,
                        month = date.month,
                    ).days
                ),
                today = date,
                selectDate = date,
                quizCalendar = mapOf(
                    date to listOf(
                        VocaQuiz(
                            date = LocalDateTime.now().toString(),
                            day = 0,
                            wrongCount = 0,
                            totalCount = 10,
                        ),
                        VocaQuiz(
                            date = LocalDateTime.now().toString(),
                            day = 2,
                            wrongCount = 3,
                            totalCount = 10,
                        ),
                        VocaQuiz(
                            date = LocalDateTime.now().toString(),
                            day = 0,
                            wrongCount = 1,
                            totalCount = 10,
                        ),
                    ),
                    Date(
                        localDate = LocalDate.now().plusDays(1)
                    ) to listOf(
                        VocaQuiz(
                            date = LocalDateTime.now().toString(),
                            day = 0,
                            wrongCount = 3,
                            totalCount = 10,
                        ),
                        VocaQuiz(
                            date = LocalDateTime.now().toString(),
                            day = 2,
                            wrongCount = 10,
                            totalCount = 10,
                        ),
                        VocaQuiz(
                            date = LocalDateTime.now().toString(),
                            day = 0,
                            wrongCount = 0,
                            totalCount = 10,
                        ),
                    ),
                    Date(
                        localDate = LocalDate.now().plusDays(2)
                    ) to listOf(
                        VocaQuiz(
                            date = LocalDateTime.now().toString(),
                            day = 0,
                            wrongCount = 0,
                            totalCount = 10,
                        ),
                        VocaQuiz(
                            date = LocalDateTime.now().toString(),
                            day = 2,
                            wrongCount = 0,
                            totalCount = 10,
                        ),
                        VocaQuiz(
                            date = LocalDateTime.now().toString(),
                            day = 0,
                            wrongCount = 1,
                            totalCount = 20,
                        ),
                    ),
                ),
                onQuizItemClick = {},
                onDateSelect = {},
            )
        }
    }
}
