package com.sandy.memorizingvoca.ui.feature.calendar.components.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
internal fun NormalCalendar(
    calendar: Calendar,
    today: Date,
    selectDate: Date,
    quizCalendar: Map<Date, List<VocaQuiz>>,
    onDateSelect: (Date) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column (
        modifier = modifier.fillMaxWidth(),
    ) {
        calendar.days.forEach { days ->
            Row(
                modifier = Modifier.weight(1f),
            ) {
                days.forEach{ date ->
                    val quizList = quizCalendar[date] ?: emptyList()
                    Column (
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clickableSelectOutline(
                                selected = selectDate == date,
                                onClick = { onDateSelect(date) },
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        DateHeader(
                            date = date,
                            isToday = date == today,
                            otherMonth = calendar.otherMonth[date] ?: false,
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(2.dp),
                            verticalArrangement = Arrangement.spacedBy(2.dp),
                        ) {
                            quizList.forEach { quiz ->
                                NormalCalendarItem(
                                    quiz = quiz,
                                )
                            }
                        }
                    }
                }
            }
            HorizontalDivider(color = Gray30)
        }
    }
}

@Composable
private fun NormalCalendarItem(
    quiz: VocaQuiz,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth(0.16f)
            .aspectRatio(1f)
            .background(
                color = quiz.correctPercentage.percentageColor(),
            )
    )
}



@Preview
@Composable
private fun NormalCalendarPreview() {
    MemorizingVocaTheme {
        val date = Date()
        Column (
            modifier = Modifier.height(400.dp)
        ) {
            NormalCalendar(
                calendar = Calendar(
                    year = date.year,
                    month = date.month,
                    days = DateUtils.createCalendar(
                        year = date.year,
                        month = date.month,
                    ).days
                ),
                today = Date(),
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
                onDateSelect = { },
            )
        }
    }
}
