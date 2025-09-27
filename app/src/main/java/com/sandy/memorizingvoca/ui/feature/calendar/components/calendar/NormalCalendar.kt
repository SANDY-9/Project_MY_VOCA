package com.sandy.memorizingvoca.ui.feature.calendar.components.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.sandy.memorizingvoca.data.model.Date
import com.sandy.memorizingvoca.data.model.VocaQuiz
import com.sandy.memorizingvoca.ui.extensions.percentageColor
import com.sandy.memorizingvoca.ui.theme.Gray30
import com.sandy.memorizingvoca.ui.theme.MemorizingVocaTheme
import com.sandy.memorizingvoca.ui.theme.roundedCornerShape4
import com.sandy.memorizingvoca.utils.DateUtils
import java.time.LocalDate
import java.time.LocalDateTime

@Composable
internal fun NormalCalendar(
    calendar: List<List<Date>>,
    month: Int,
    today: Date,
    selectDate: Date?,
    quizCalendar: Map<Date, List<VocaQuiz>>,
    onDateSelect: (Date, Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column (
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        calendar.forEachIndexed { weekIndex, days ->
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                days.forEach { date ->
                    val quizList = quizCalendar[date] ?: emptyList()
                    FlowRow (
                        modifier = Modifier.weight(1f),
                        horizontalArrangement = Arrangement.spacedBy(2.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        DateHeader(
                            date = date,
                            isToday = date == today,
                            otherMonth = month != date.month,
                        )
                        quizList.forEach { quiz ->
                            NormalCalendarItem(
                                quiz = quiz,
                            )
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
            .padding(start = 1.dp)
            .fillMaxWidth(0.15f)
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
        Box(
            modifier = Modifier.fillMaxSize().fillMaxHeight(0.5f)
        ) {
            NormalCalendar(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(0.5f),
                calendar = DateUtils.createCalendar(
                    year = date.year,
                    month = date.month,
                ).days,
                month = date.month,
                today = Date(),
                selectDate = null,
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
                onDateSelect = { _, _-> },
            )
        }
    }
}
